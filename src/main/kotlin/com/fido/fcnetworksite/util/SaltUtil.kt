package com.fido.fcnetworksite.util

import java.util.*

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 17:41 2018/10/18
 */
object SaltUtils {
    fun createSalt(): String {
        return UUID.randomUUID().toString().substring(0, 6)
    }
}

fun main(args: Array<String>) {
    println(SaltUtils.createSalt())
}