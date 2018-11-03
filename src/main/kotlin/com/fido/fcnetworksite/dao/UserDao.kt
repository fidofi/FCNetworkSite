package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.entity.UserEntity
import org.apache.ibatis.annotations.Param
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
    fun saveUser(user: UserEntity)

    /**
     * 更新用户基本信息
     */

    fun updateUserInfo(@Param("user") user: UserEntity)

    /**
     * 修改用户密码
     */

    fun updateUserPassword(@Param("password") password: String,
                           @Param("salt") salt: String,
                           @Param("email") email: String)

    /**
     * 根据userId查找用户
     */

    fun findUserById(@Param("userId") userId: Long): UserEntity?

    /**
     * 根据email查找用户
     */
    fun findUserByEmail(@Param("email") email: String): UserEntity?


    /**
     * 根据用户昵称模糊查找用户
     */

    fun findUserLikeName(@Param("nickName") nickName: String): List<UserEntity>

    /**
     * 根据用户昵称精确查找用户
     */

    fun findUserByName(@Param("nickName") nickName: String): UserEntity

    /**
     * 冻结用户
     */

    fun freezeUser(@Param("userId") userId: Long)

    /**
     * 取消冻结用户
     */

    fun unFreezeUser(@Param("userId") userId: Long)

    /**
     * 批量查找用户
     */
    fun batchListUser(@Param("userIdList") userIdList: List<Long>): List<UserEntity>


}