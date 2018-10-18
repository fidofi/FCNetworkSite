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
    var email: String = ""
    var nickName: String = ""
    var password: String = ""
    var sex: SexEnum = SexEnum.MALE
    var createTime: LocalDateTime = LocalDateTime.now()
    var updateTime: LocalDateTime = LocalDateTime.now()
    var birthday: LocalDate = LocalDate.now()
    var photoUrl: String = ""
    var salt: String = ""
    var status: Int = 0

    constructor(userId: Long, email: String, nickName: String, sex: SexEnum, birthday: LocalDate, photoUrl: String) {
        this.userId = userId
        this.email = email
        this.nickName = nickName
        this.sex = sex
        this.birthday = birthday
        this.photoUrl = photoUrl
    }

    constructor()
}


