package com.fido.fcnetworksite.entity

import java.time.LocalDateTime

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/25 22:16
 * @description:
 */
class MoodEntity {
    var moodId: Int = 0
    var content: String = ""
    var userId: Long = 0
    var createTime: LocalDateTime = LocalDateTime.now()
    var updateTime: LocalDateTime = LocalDateTime.now()
    var isDeleted: Int = 0
    var commentCount: Long = 0
    var likeCount: Long = 0

    constructor()
    constructor(content: String, userId: Long) {
        this.content = content
        this.userId = userId
    }

}