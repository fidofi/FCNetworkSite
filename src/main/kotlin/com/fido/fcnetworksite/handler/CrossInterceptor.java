package com.fido.fcnetworksite.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/18 21:32
 * @description:
 */
public class CrossInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(CrossInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o)
            throws Exception {
        logger.info("inside cross interceptor");
        if (httpServletRequest.getHeader(HttpHeaders.ORIGIN) != null) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader(
                    "Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
            httpServletResponse.setHeader(
                    "Access-Control-Allow-Headers",
                    "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
            httpServletResponse.setHeader("XDomainRequestAllowed", "1");
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            ModelAndView modelAndView)
            throws Exception {}

    @Override
    public void afterCompletion(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e)
            throws Exception {}
}
