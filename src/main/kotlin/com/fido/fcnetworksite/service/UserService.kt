package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.vo.UserVo

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 16:25 2018/10/18
 */
interface UserService {
    fun saveUser(userVo: UserVo)

    fun updateUserInfo(userVo: UserVo)

    fun updateUserPassword(email: String, newPassword: String, oldPassword: String)

    fun freezeUser(userId: Long)

    fun unFreezeUser(userId: Long)

    fun selectUserByName(nickName: String): UserVo

    fun selectUserLikeName(nickName: String): List<UserVo>

    fun selectUserByEmail(email: String): UserVo

    fun selectUserById(userId: Long): UserVo
}