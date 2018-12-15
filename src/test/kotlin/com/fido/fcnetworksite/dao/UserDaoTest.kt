package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.AbstractUnitTest
import com.fido.fcnetworksite.constant.LIKE_PREFIX
import com.fido.fcnetworksite.entity.UserEntity
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import java.time.LocalDate

/**
 * @author: fido
 * @desription:
 * @date: Created in 10:18 2018/10/12
 */

class UserDaoTest : AbstractUnitTest() {
    @Autowired
    lateinit var userDao: UserDao
    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String,Any>
    @Autowired
    lateinit var stringRedisTemplate: StringRedisTemplate
    @Autowired
    private lateinit var zSetOperations: ZSetOperations<String, Any>

    @Test
    fun testSaveUser() {

        var userEntity: UserEntity = UserEntity()
        userEntity.nickName = "fido111"
        userEntity.birthday = LocalDate.parse("2018-10-10")
        userEntity.email = "111"
        userEntity.password = "333"
        userDao.saveUser(userEntity)

    }

    @Test
    fun testUpdateUserInfo() {
        println(redisTemplate.keys(LIKE_PREFIX+"*"))
    }

    @Test
    fun testFindByEmail() {
        println(userDao.findUserByEmail("fromwxf@163.com"))
    }

    @Test
    fun testRedis() {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111")
        zSetOperations.add("1", 1, 1.0)

    }

    @Test
    fun testSelectUser() {
        println(userDao.batchListUser(listOf(1)))
    }


}