package com.fido.fcnetworksite.util

import org.springframework.util.DigestUtils
import java.io.UnsupportedEncodingException

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 17:44 2018/10/18
 */
object MD5Util {

    private const val UTF_8 = "UTF-8"

    /**
     * MD5加密
     *
     * @param sourceStr
     * @return 32位密文
     * @throws Exception
     */
    fun encrypt(sourceStr: String): String {
        var targetStr = ""
        try {
            targetStr = DigestUtils.md5DigestAsHex(sourceStr.toByteArray(charset(UTF_8)))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return targetStr
    }
}