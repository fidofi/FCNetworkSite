package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.constant.PhotoConstant
import com.fido.fcnetworksite.dao.UserDao
import com.fido.fcnetworksite.entity.UserEntity
import com.fido.fcnetworksite.enum.SexEnum
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.exception.BaseException
import com.fido.fcnetworksite.exception.UserException
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.MD5Util
import com.fido.fcnetworksite.util.SaltUtils
import com.fido.fcnetworksite.util.UserInfoHolder
import com.fido.fcnetworksite.vo.UserVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpRequest
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

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
        val user = userDao.findUserByEmail(userVo.email)
        if (user != null) {
            throw BaseException(StatusEnum.EMAIL_REPEAT)
        }
        val userEntity = UserEntity(userVo.userId, userVo.email, userVo.nickName,
                SexEnum.getSexEnumByCode(userVo.sex), userVo.birthday, userVo.photoUrl
                ?: PhotoConstant.DEFAULT_PHOTO_URL
                ?: PhotoConstant.DEFAULT_PHOTO_URL, userVo.password)
        val salt = SaltUtils.createSalt()
        val password = MD5Util.encrypt(userVo.password + salt)
        userEntity.salt = salt
        userEntity.password = password
        userDao.saveUser(userEntity)
    }

    override fun updateUserInfo(userVo: UserVo) {
        userDao.updateUserInfo(UserEntity(userVo.userId, userVo.email, userVo.nickName,
                SexEnum.getSexEnumByCode(userVo.sex), userVo.birthday,
                userVo.photoUrl ?: PhotoConstant.DEFAULT_PHOTO_URL, userVo.introduction))
    }

    override fun updateUserPassword(email: String, newPassword: String, oldPassword: String) {
        val originUser = userDao.findUserByEmail(email) ?: throw UserException(StatusEnum.USER_NOT_EXIST)
        val originPassword = MD5Util.encrypt(oldPassword + originUser.salt)
        //旧密码输入正确
        if (originUser.password == originPassword) {
            val newSalt = SaltUtils.createSalt()
            val password = MD5Util.encrypt(newPassword + newSalt)
            userDao.updateUserPassword(password, newSalt, email)
        }
        //旧密码输入不正确
        else {
            throw UserException(StatusEnum.PASSWORD_NOT_MATCH)
        }
    }

    override fun login(email: String, password: String) {
        val user = userDao.findUserByEmail(email) ?: throw UserException(StatusEnum.USER_NOT_EXIST)
        val transferPassword = MD5Util.encrypt(password + user.salt)
        if (transferPassword != user.password) {
            throw  UserException(StatusEnum.PASSWORD_NOT_MATCH)
        }
        //登陆成功,存储用户信息
        val userVo = UserVo(user.userId, user.email, user.nickName, user.sex.code, user.birthday, user.photoUrl, user.introduction)

        UserInfoHolder.initLocal(userVo)
    }

    override fun freezeUser(userId: Long) {
        userDao.freezeUser(userId)
    }

    override fun unFreezeUser(userId: Long) {
        userDao.unFreezeUser(userId)
    }

    override fun selectUserById(userId: Long): UserVo {
        val userEntity = userDao.findUserById(userId) ?: throw UserException(StatusEnum.USER_NOT_EXIST)
        return UserVo(userEntity.userId, userEntity.email, userEntity.nickName, userEntity.sex.code,
                userEntity.birthday, userEntity.photoUrl, userEntity.introduction)
    }

    override fun selectUserByName(nickName: String): UserVo {
        val userEntity = userDao.findUserByName(nickName)
        return UserVo(userEntity.userId, userEntity.email, userEntity.nickName, userEntity.sex.code,
                userEntity.birthday, userEntity.photoUrl, userEntity.introduction)
    }

    override fun selectUserLikeName(nickName: String): List<UserVo> {
        val userList = userDao.findUserLikeName(nickName)
        return userList.map { UserVo(it.userId, it.email, it.nickName, it.sex.code, it.birthday, it.photoUrl, it.introduction) }
    }

    override fun selectUserByEmail(email: String): UserVo {
        val userEntity = userDao.findUserByEmail(email) ?: throw UserException(StatusEnum.USER_NOT_EXIST)
        return UserVo(userEntity.userId, userEntity.email,
                userEntity.nickName, userEntity.sex.code, userEntity.birthday, userEntity.photoUrl, userEntity.introduction)
    }

    override fun batchSelectUser(userIdList: List<Long>): List<UserVo> {
        return userDao.batchListUser(userIdList).map { UserVo(it.userId, it.email, it.nickName, it.sex.code, it.birthday, it.photoUrl, it.introduction) }
    }
}