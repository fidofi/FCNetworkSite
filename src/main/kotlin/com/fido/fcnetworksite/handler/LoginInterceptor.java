package com.fido.fcnetworksite.handler;

import com.fido.fcnetworksite.constant.PrefixConstant;
import com.fido.fcnetworksite.util.UserInfoUtils;
import com.fido.fcnetworksite.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/17 21:27
 * @description:
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String uri = request.getRequestURI();
        logger.info("inside interceptor,uri:" + uri);
        if (uri.equals("/fcnetworksite/v1/user/login")) {
            return true;
        }
        HttpSession session = request.getSession();
        logger.info("interceptor session:" + session);
        // 会话暂未失效
        if (session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) != null) {
            logger.info("session set==" + session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX));
            if (UserInfoUtils.getUserVo() == null) {
                UserInfoUtils.setUserVo(
                        (UserVo) session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX));
            }
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println(
                ">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
