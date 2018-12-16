package com.fido.fcnetworksite.config;

import com.fido.fcnetworksite.handler.CorsInterceptor;
import com.fido.fcnetworksite.handler.UserInfoInterceptor;
import com.fido.fcnetworksite.resolver.JsonParamArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 20:33
 * @description:
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserInfoInterceptor userInfoInterceptor() {
        return new UserInfoInterceptor();
    }

    @Bean
    public JsonParamArgumentResolver jsonParamArgumentResolver() {
        return new JsonParamArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonParamArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    //    @Override
    //    public void addCorsMappings(CorsRegistry registry) {
    //        registry.addMapping("/**")
    //                .allowedOrigins("*")
    //                .allowCredentials(true)
    //                .allowedMethods("GET", "POST", "DELETE", "PUT")
    //                .maxAge(3600);
    //    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor());
        registry.addInterceptor(new UserInfoInterceptor());
        super.addInterceptors(registry);
    }
}
