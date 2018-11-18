package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.RelationService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.vo.RelationVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:19 2018/11/8
 */
@RestController
@RequestMapping("/v1/relation")
class RelationController {
    @Autowired
    private lateinit var relationService: RelationService

    /**
     * 关注某人
     */
    @PostMapping("/follow")
    fun followSomeOne(@RequestBody relationVo: RelationVo): DataMap {
        relationService.followSomeOne(relationVo.followingId, relationVo.followedId)
        return ResponseBuilder.create().ok().build()
    }

    /**
     * 取关某人
     */
    @PostMapping("/unfollow")
    fun unFollowSomeOne(@RequestBody relationVo: RelationVo): DataMap {
        relationService.unFollowSomeOne(relationVo.followingId, relationVo.followedId)
        return ResponseBuilder.create().ok().build()
    }

    /**
     * 获取粉丝
     */
    @GetMapping("/fans-list/{userId}")
    fun getFans(@PathVariable("userId") userId: Long,
                @RequestParam("pageIndex") pageIndex: Int,
                @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().data(relationService.getFans(userId, pageIndex, pageSize)).build()
    }

    /**
     * 获取关注的列表
     */
    @GetMapping("/following-list/{userId}")
    fun getFollowingList(@PathVariable("userId") userId: Long,
                         @RequestParam("pageIndex") pageIndex: Int,
                         @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().data(relationService.getFollowingList(userId, pageIndex, pageSize)).build()
    }
}