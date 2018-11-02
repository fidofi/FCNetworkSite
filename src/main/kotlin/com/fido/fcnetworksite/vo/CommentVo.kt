package com.fido.fcnetworksite.vo

import java.time.LocalDateTime

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/2 22:57
 * @description:
 */
data class CommentVo(val moodId: Int, val commentId: Int, val userId: Int, val createTime: LocalDateTime)