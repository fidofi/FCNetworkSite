package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.entity.User
import org.springframework.stereotype.Repository

/**
 * @author: fido
 * @desription:
 * @date: Created in 9:33 2018/10/12
 */
@Repository
interface UserDao {
    /**
     * 保存用户
     */
    fun saveUser(user: User)

//    /**
//     * 更新用户基本信息
//     */
//
//    fun updateUserInfo(user: User)
//
//    /**
//     * 修改用户密码
//     */
//
//    fun updateUserPassword(@Param("oldPassword") oldPassword: String, @Param("newPassword") newPassword: String)
//
//    /**
//     * 根据userId查找用户
//     */
//
//    fun findUserById(userId: Long): User
//
//    /**
//     * 根据用户昵称模糊查找用户
//     */
//
//    fun findUserLikeName(nickName: String): List<User>
//
//    /**
//     * 冻结用户
//     */
//
//    fun freezeUser(userId: Long)
//
//    /**
//     * 取消冻结用户
//     */
//
//    fun unFreezeUser(userId: Long)


}