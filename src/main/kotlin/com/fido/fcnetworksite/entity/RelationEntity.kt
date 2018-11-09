package com.fido.fcnetworksite.entity

import java.time.LocalDateTime

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 18:46 2018/11/8
 */
class RelationEntity {
    var relationId: Long = 0
    var followingId: Long = 0
    var followedId: Long = 0
    var followTime: LocalDateTime = LocalDateTime.now()

    constructor()

    constructor(followingId: Long, followedId: Long) {
        this.followingId = followingId
        this.followedId = followedId
    }
}