package com.fido.fcnetworksite.entity

import java.time.LocalDateTime

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:31 2018/11/2
 */
class CommentEntity {
    var commentId: Int = 0
    var content: String = ""
    var moodId: Int = 0
    var userId: Long = 0
    var createTime: LocalDateTime = LocalDateTime.now()
    var updateTime: LocalDateTime = LocalDateTime.now()
    var isRead: Int = 0
    var isDeleted: Int = 0

    constructor()
    constructor(content: String, moodId: Int, userId: Long) {
        this.content = content
        this.moodId = moodId
        this.userId = userId
    }


}
