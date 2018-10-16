package com.fido.fcnetworksite.entity

import com.fido.fcnetworksite.enum.SexEnum
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * @author: fido
 * @date: 2018/10/11 23:02
 * @description:用户表
 */

class UserEntity {
    var userId: Long = 0
    var email: String? = null
    var nickName: String? = null
    var password: String? = null
    var sex: SexEnum = SexEnum.MALE
    var createTime: LocalDateTime = LocalDateTime.now()
    var updateTime: LocalDateTime = LocalDateTime.now()
    var birthday: LocalDate = LocalDate.now()
    var photoUrl: String? = null
    var salt: String? = null
    var status: Int = 0

}

