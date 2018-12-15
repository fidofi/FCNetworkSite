package com.fido.fcnetworksite.util

import com.fido.fcnetworksite.constant.PrefixConstant
import com.fido.fcnetworksite.vo.UserVo
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/1 23:03
 * @description:
 */
object UserInfoHolder {
    //    private val threadLocal = ThreadLocal<UserVo>()
//    internal fun initLocal(userVo: UserVo) {
//        threadLocal.set(userVo)
//    }
//
//    val userInfo
//        get() = threadLocal.get()
//
//    val userId
//        get() = threadLocal.get().userId
//
//    internal fun clearLocal() {
//        threadLocal.remove()
//    }
    var userVo: UserVo
        get() {
            val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
            val session = request.session
            return session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) as UserVo
        }
        set(userVo) {
            val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
            val session = request.session
            session.setAttribute(PrefixConstant.SESSION_INFO_PREFIX, userVo)
        }


}