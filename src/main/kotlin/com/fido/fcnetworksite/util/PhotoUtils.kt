package com.fido.fcnetworksite.util

import java.time.LocalDateTime

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/3 21:28
 * @description:
 */
object PhotoUtils {
    fun getName(userId: Long): String {
        return MD5Util.encrypt(LocalDateTime.now().toString() + "-" + userId).substring(0, 10) + "-" + userId + ".jpg"
    }
}