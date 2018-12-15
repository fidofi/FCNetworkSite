package com.fido.fcnetworksite.constant

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 16:47 2018/10/18
 */
object PhotoConstant {
    const val IP = "47.106.199.194"
    const val USERNAME = "ftpuser"
    const val PASSWORD = "ftpuser"
    const val DIRECTORY = "/home/ftpuser/www/images"
    const val PHOTO_URL_PREFIX = "$IP/images/"
    //默认的图片地址
    const val DEFAULT_PHOTO_URL = PHOTO_URL_PREFIX + "default.jpg"

}

object PrefixConstant {
    const val SESSION_INFO_PREFIX = "userInfo"
}

const val LIKE_PREFIX = "like_mood:"
const val COMMENT_PREFIX = "comment_mood:"