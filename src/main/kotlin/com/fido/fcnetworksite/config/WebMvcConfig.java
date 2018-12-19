package com.fido.fcnetworksite.config;

import com.fido.fcnetworksite.handler.CrossInterceptor;
import com.fido.fcnetworksite.handler.LoginInterceptor;
import com.fido.fcnetworksite.handler.UserInfoInterceptor;
import com.fido.fcnetworksite.resolver.JsonParamArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CrossInterceptor());
        registry.addInterceptor(new LoginInterceptor());
    }
}
