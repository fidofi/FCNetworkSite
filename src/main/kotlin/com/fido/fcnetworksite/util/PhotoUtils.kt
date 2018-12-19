package com.fido.fcnetworksite.util

import java.time.LocalDateTime
import kotlin.random.Random

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/3 21:28
 * @description:
 */
object PhotoUtils {
    fun getName(userId: Long): String {
        return MD5Util.encrypt(LocalDateTime.now().toString() + "-" + Random.nextLong(100)).substring(0, 10) + "-" + userId + ".jpg"
    }
}