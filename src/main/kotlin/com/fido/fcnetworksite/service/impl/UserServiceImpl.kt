package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.UserDao
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.exception.UserException
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.MD5Util
import com.fido.fcnetworksite.util.SaltUtils
import com.fido.fcnetworksite.vo.UserVo
import com.fido.fcnetworksite.vo.toUserEntity
import com.fido.fcnetworksite.vo.toUserVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 17:05 2018/10/18
 */
@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var userDao: UserDao

    override fun saveUser(userVo: UserVo) {
        userDao.saveUser(userVo.toUserEntity(userVo))
    }

    override fun updateUserInfo(userVo: UserVo) {
        userDao.updateUserInfo(userVo.toUserEntity(userVo))

    }

    override fun updateUserPassword(email: String, newPassword: String, oldPassword: String) {
        val originUser = userDao.findUserByEmail(email)
        val originPassword = MD5Util.encrypt(oldPassword + originUser.salt)
        //旧密码输入正确
        if (originUser.password == originPassword) {
            val newSalt = SaltUtils.createSalt()
            val password = MD5Util.encrypt(newPassword + newSalt)
            userDao.updateUserPassword(password, newSalt, email)
        }
        //旧密码输入不正确
        else {
            throw UserException(StatusEnum.PASSWORD_NOT_MATCH, "密码错误")
        }
    }
    override fun freezeUser(userId: Long) {
        userDao.freezeUser(userId)
    }

    override fun unFreezeUser(userId: Long) {
        userDao.unFreezeUser(userId)
    }

    override fun selectUserById(userId: Long): UserVo {
        val userEntity = userDao.findUserById(userId)
        return userEntity.toUserVo(userEntity)
    }

    override fun selectUserByName(nickName: String): UserVo {
        val userEntity = userDao.findUserByName(nickName)
        return userEntity.toUserVo(userEntity)
    }

    override fun selectUserLikeName(nickName: String): List<UserVo> {
        val userList = userDao.findUserLikeName(nickName)
        return userList.map { it.toUserVo(it) }
    }

    override fun selectUserByEmail(email: String): UserVo {
        val userEntity = userDao.findUserByEmail(email)
        return userEntity.toUserVo(userEntity)
    }

}