package com.fido.fcnetworksite.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/16 15:51
 * @description:
 */
public class CorsInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(UserInfoInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String method = request.getMethod();
        logger.info("inside corsinterceptor");
        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader(
                    "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
        }
        if ("OPTIONS".equals(method)) { // 检测是options方法则直接返回200
            response.setStatus(HttpStatus.OK.value());
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
