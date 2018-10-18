package com.fido.fcnetworksite.vo

import com.fido.fcnetworksite.constant.PhotoUrl
import com.fido.fcnetworksite.entity.UserEntity
import com.fido.fcnetworksite.enum.SexEnum
import java.time.LocalDate

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 16:40 2018/10/18
 */
class UserVo(val userId: Long,
             val email: String,
             val nickName: String,
             val sex: SexEnum = SexEnum.MALE, val birthday: LocalDate = LocalDate.now(),
             val photoUrl: String = PhotoUrl.DEFAULT_PHOTO_URL)

fun UserVo.toUserEntity(userVo: UserVo): UserEntity {
    return UserEntity(this.userId, this.email, this.nickName, this.sex, this.birthday, this.photoUrl)
}

fun UserEntity.toUserVo(userEntity: UserEntity): UserVo {
    return UserVo(this.userId, this.email, this.nickName, this.sex, this.birthday, this.photoUrl)
}