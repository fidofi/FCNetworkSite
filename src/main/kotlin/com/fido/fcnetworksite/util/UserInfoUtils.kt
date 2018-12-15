package com.fido.fcnetworksite.util

import com.fido.fcnetworksite.constant.PrefixConstant
import com.fido.fcnetworksite.dto.UserLoginDto
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/16 02:03
 * @description:
 */
object UserInfoUtils {

    var userLoginDto: UserLoginDto
        get() {
            val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
            val session = request.session
            return session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) as UserLoginDto
        }
        set(userLoginDto) {
            val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
            val session = request.session
            session.setAttribute(PrefixConstant.SESSION_INFO_PREFIX, userLoginDto)
        }
}
