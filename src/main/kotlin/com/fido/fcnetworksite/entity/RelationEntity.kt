package com.fido.fcnetworksite.entity

import com.fido.fcnetworksite.enum.RelationEnum
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
    var followingTime: LocalDateTime = LocalDateTime.now()
    var followedTime: LocalDateTime = LocalDateTime.now()
    var status: Int = RelationEnum.UNILATERAL_CONCERN.status

    constructor()
    constructor(relationId: Long, followingId: Long, followedId: Long, followingTime: LocalDateTime, followedTime: LocalDateTime, status: Int) {
        this.relationId = relationId
        this.followingId = followingId
        this.followedId = followedId
        this.followingTime = followingTime
        this.followedTime = followedTime
        this.status = status
    }


}