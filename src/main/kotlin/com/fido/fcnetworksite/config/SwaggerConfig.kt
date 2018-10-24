package com.fido.fcnetworksite.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 14:20 2018/10/24
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                //指定要生成api文档的根包
                .apis(RequestHandlerSelectors.basePackage("com.fido.fcnetworksite.controller"))
                //路径配置
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())

    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Swagger2的Restful API 文档")
                .description("Spring Boot和Swagger的结合")
                .version("1.0")
                .build()
    }
}