package com.fido.fcnetworksite.entity

import java.time.LocalDateTime

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 18:33 2018/11/2
 */
class PhotoEntity {
    var moodId: Int = 0
    var photoUrl: String = ""
    var order: Int = 0
    var id: Int = 0
    val createTime: LocalDateTime = LocalDateTime.now()

    constructor()

    constructor(moodId: Int, photoUrl: String, order: Int) {
        this.moodId = moodId
        this.photoUrl = photoUrl
        this.order = order
    }

}