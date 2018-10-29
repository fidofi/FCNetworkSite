package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.AbstractUnitTest
import com.fido.fcnetworksite.entity.UserEntity
import com.fido.fcnetworksite.enum.SexEnum
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.FileInputStream
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author: fido
 * @desription:
 * @date: Created in 10:18 2018/10/12
 */

class UserDaoTest : AbstractUnitTest() {
    @Autowired
    lateinit var userDao: UserDao

    @Test
    fun testSaveUser() {
        var userEntity: UserEntity = UserEntity()
        userEntity.nickName = "fido111"
        userEntity.birthday = LocalDate.parse("2018-10-10")


    }

    @Test
    fun testUpdateUserInfo() {
        var userEntity: UserEntity = userDao.findUserById(1)
        userEntity.nickName = "fido飞飞"
        userDao.updateUserInfo(userEntity)

    }

}