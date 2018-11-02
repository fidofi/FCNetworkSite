package com.fido.fcnetworksite.vo

import javax.validation.constraints.NotBlank


/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/25 22:46
 * @description:
 */
data class MoodVo(
        val moodId: Int,
        val userId: Long,
        val userName: String,
        @NotBlank(message = "内容不能为空") val content: String,
        val commentCount: Long,
        val likeCount: Long,
        val photoList: List<PhotoVo>)