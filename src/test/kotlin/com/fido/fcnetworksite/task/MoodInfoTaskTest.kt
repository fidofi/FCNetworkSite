package com.fido.fcnetworksite.task

import com.fido.fcnetworksite.AbstractUnitTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/16 00:57
 * @description:
 */
class MoodInfoTaskTest : AbstractUnitTest() {
    @Autowired
    private lateinit var moodInfoTask: MoodInfoTask

    @Test
    fun synCommentAndLikeCount() {
        moodInfoTask.synCommentAndLikeCount()
    }
}