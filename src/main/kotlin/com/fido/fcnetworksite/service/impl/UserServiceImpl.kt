package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.constant.PrefixConstant
import com.fido.fcnetworksite.dao.UserDao
import com.fido.fcnetworksite.entity.UserEntity
import com.fido.fcnetworksite.enum.SexEnum
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.exception.BaseException
import com.fido.fcnetworksite.exception.UserException
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.MD5Util
import com.fido.fcnetworksite.util.SaltUtils
import com.fido.fcnetworksite.util.UserInfoUtils
import com.fido.fcnetworksite.vo.PageInfoVo
import com.fido.fcnetworksite.vo.UserVo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 17:05 2018/10/18
 */
@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var userDao: UserDao
    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    override fun saveUser(userVo: UserVo) {
        val user = userDao.findUserByEmail(userVo.email)
        if (user != null) {
            throw BaseException(StatusEnum.EMAIL_REPEAT)
        }
        val userEntity = UserEntity(userVo.userId, userVo.email, userVo.nickName,
                SexEnum.getSexEnumByCode(userVo.sex), userVo.birthday, userVo.photoUrl, userVo.password)
        val salt = SaltUtils.createSalt()
        val password = MD5Util.encrypt(userVo.password + salt)
        userEntity.salt = salt
        userEntity.password = password
        userDao.saveUser(userEntity)
    }

    override fun updateUserInfo(userVo: UserVo) {
        userDao.updateUserInfo(UserEntity(userVo.userId, userVo.email, userVo.nickName,
                SexEnum.getSexEnumByCode(userVo.sex), userVo.birthday,
                userVo.photoUrl, userVo.introduction))
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

    override fun login(email: String, password: String): UserVo {
        val user = userDao.findUserByEmail(email) ?: throw UserException(StatusEnum.USER_NOT_EXIST)
        val transferPassword = MD5Util.encrypt(password + user.salt)
        if (transferPassword != user.password) {
            throw  UserException(StatusEnum.PASSWORD_NOT_MATCH)
        }
        if (user.status == 2 || user.status == 3) {
            throw UserException(StatusEnum.EMAIL_REPEAT)
        }
        //登陆成功,存储用户信息
        val userVo = UserVo(user.userId, user.email, user.nickName, user.sex.code, user.birthday, user.photoUrl, user.introduction)
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        logger.info("request:${request.session}")
        request.session.setAttribute(PrefixConstant.SESSION_INFO_PREFIX, userVo)
        UserInfoUtils.setUserVo(userVo)
        return userVo
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

    override fun selectUserByName(nickName: String): UserVo? {
        val userEntity = userDao.findUserByName(nickName)
        if (userEntity == null) {
            return null
        }
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
        return if (userIdList.isNotEmpty()) {
            userDao.batchListUser(userIdList).map { UserVo(it.userId, it.email, it.nickName, it.sex.code, it.birthday, it.photoUrl, it.introduction) }
        } else {
            emptyList()
        }
    }

    override fun passUser(userId: Long) {
        userDao.passUser(userId)
    }

    override fun reject(userId: Long) {
        userDao.rejectUser(userId)
    }

    override fun selectUserByCondition(state: Int, userName: String?, page: Int, pageSize: Int): PageInfoVo<UserVo> {
        val total = userDao.getTotal(state, userName)
        val start = page * pageSize
        val result = userDao.selectUser(state, userName, start, pageSize).map {
            UserVo(it.userId, it.email, it.nickName, it.sex.code, it.birthday, it.photoUrl, it.introduction, status = it.status)
        }
        return PageInfoVo(total, page, pageSize, result)
    }
}