package com.fido.fcnetworksite.handler

import com.fido.fcnetworksite.constant.PrefixConstant
import com.fido.fcnetworksite.util.UserInfoHolder
import com.fido.fcnetworksite.vo.UserVo
import org.slf4j.LoggerFactory
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/1 23:21
 * @description:
 */
class UserInfoInterceptor : HandlerInterceptorAdapter() {
    private val logger = LoggerFactory.getLogger(UserInfoInterceptor::class.java)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse,
                           handler: Any): Boolean {
        val uri = request.requestURI
        if (uri == "/fcnetworksite/v1/user/login") {
            return true
        }
        val httpRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val session = httpRequest.session
        logger.info("interceptor session:" + session)
        //会话暂未失效
        if (session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) != null) {
            logger.info("session set==" + session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX))
            if (UserInfoHolder.userInfo == null) {
                UserInfoHolder.initLocal(session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) as UserVo)
            }
            return super.preHandle(request, response, handler)
        }
        return false
    }
}