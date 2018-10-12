package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.AbstractUnitTest
import com.fido.fcnetworksite.entity.User
import com.fido.fcnetworksite.enum.SexEnum
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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
        val user: User = User(0, "984813437@qq.com", "fido", "EA48576F30BE1669971699C09AD05C94", SexEnum.FEMALE,
                LocalDateTime.now(), LocalDateTime.now(), LocalDate.now(), "http://localhost:8080", "123456", 0)
        userDao.saveUser(user)

    }
}