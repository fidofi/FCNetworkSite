package com.fido.fcnetworksite.config;

import com.fido.fcnetworksite.handler.UserInfoInterceptor;
import com.fido.fcnetworksite.resolver.JsonParamArgumentResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 20:33
 * @description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new JsonParamArgumentResolver());
    super.addArgumentResolvers(argumentResolvers);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new UserInfoInterceptor());
  }
}
