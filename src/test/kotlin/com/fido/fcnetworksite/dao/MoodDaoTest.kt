package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.AbstractUnitTest
import com.fido.fcnetworksite.entity.MoodEntity
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/16 00:02
 * @description:
 */
class MoodDaoTest : AbstractUnitTest() {
    @Autowired
   private lateinit var moodDao:MoodDao

    @Test
    fun testUpdateMood() {
        val moodEntity = MoodEntity(1, 1, 1)
        val moodEntity2=MoodEntity(2,2,2)
        moodDao.batchUpdateMoodInfo(listOf(moodEntity,moodEntity2))
    }
}