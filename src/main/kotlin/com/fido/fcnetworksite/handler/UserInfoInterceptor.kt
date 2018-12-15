package com.fido.fcnetworksite.handler

import com.fido.fcnetworksite.util.UserInfoHolder
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/1 23:21
 * @description:
 */
class UserInfoInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse,
                           handler: Any): Boolean {
        val uri = request.requestURI
        if (uri == "/fcnetworksite/v1/user/login") {
            return true
        }
        val session = request.session
        //会话暂未失效
        if (UserInfoHolder.userVo != null) {
//            if (UserInfoHolder.userInfo == null) {
//                UserInfoHolder.initLocal(session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) as UserVo)
//            }
            return true
        }
        return false
    }
}