package com.fido.fcnetworksite.entity

import java.time.LocalDateTime

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:31 2018/11/2
 */
class CommentEntity {
    var id: Int = 0
    var content: String = ""
    var moodId: Int = 0
    var userId: Int = 0
    var createTime: LocalDateTime = LocalDateTime.now()
    var updateTime: LocalDateTime = LocalDateTime.now()
    var isDeleted: Int = 0
}