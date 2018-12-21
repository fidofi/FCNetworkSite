package com.fido.fcnetworksite.util

import java.time.LocalDateTime
import kotlin.random.Random

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/3 21:28
 * @description:
 */
object PhotoUtils {
    fun getName(): String {
        return MD5Util.encrypt(LocalDateTime.now().toString() + "-" + java.util.Random().nextInt(1000)).substring(0, 10) + "-" +java.util.Random().nextInt(1000) + ".jpg"
    }
}