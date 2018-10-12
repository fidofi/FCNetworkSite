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

data class User(
        val userId: Long = 0,
        val email: String,
        val nickName: String,
        val password: String,
        val sex: SexEnum,
        val createTime: LocalDateTime = LocalDateTime.now(),
        val updateTime: LocalDateTime = LocalDateTime.now(),
        val birthday: LocalDate,
        val photoUrl: String,
        val salt: String,
        val status: Int = 0
)
