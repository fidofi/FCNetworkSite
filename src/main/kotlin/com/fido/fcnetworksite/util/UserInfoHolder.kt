package com.fido.fcnetworksite.util

import com.fido.fcnetworksite.vo.UserVo

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/1 23:03
 * @description:
 */
object UserInfoHolder {
    private val threadLocal = ThreadLocal<UserVo>()
    internal fun initLocal(userVo: UserVo) {
        threadLocal.set(userVo)
    }
    val userInfo
        get() = threadLocal.get()

    val userId
        get() = threadLocal.get().userId

    internal fun clearLocal() {
        threadLocal.remove()
    }
}