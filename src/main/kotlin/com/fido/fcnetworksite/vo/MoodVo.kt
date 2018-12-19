package com.fido.fcnetworksite.vo

import java.time.LocalDateTime


/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/25 22:46
 * @description:
 */
data class MoodVo(
        val moodId: Int = 0,
        val userId: Long = 0,
        val userName: String = "",
        val content: String = "",
        val commentCount: Long = 0,
        val likeCount: Long = 0,
        val userPhoto: String = "",
        val photoList: List<String>?,
        val createTime: LocalDateTime = LocalDateTime.now(),
        val state:Int=0)