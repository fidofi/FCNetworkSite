package com.fido.fcnetworksite.dto

import com.fido.fcnetworksite.enum.SexEnum
import java.time.LocalDate

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/16 02:05
 * @description:
 */
class UserLoginDto {
    var userId: Long = 0
    var email: String = ""
    var nickName: String = ""
    var password: String = ""
    var sex: SexEnum = SexEnum.MALE
    var birthday: LocalDate = LocalDate.now()
    var photoUrl: String = ""
    var salt: String = ""
    var status: Int = 0
    var introduction: String = ""
}