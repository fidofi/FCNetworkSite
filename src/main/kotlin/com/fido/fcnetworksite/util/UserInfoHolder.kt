package com.fido.fcnetworksite.util

import com.fido.fcnetworksite.vo.UserVo
import org.slf4j.LoggerFactory

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/1 23:03
 * @description:
 */
object UserInfoHolder {
    private val logger = LoggerFactory.getLogger(UserInfoHolder::class.java)
    private val threadLocal = ThreadLocal<UserVo>()
    internal fun initLocal(userVo: UserVo) {
        logger.info("===login:$userVo")
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
