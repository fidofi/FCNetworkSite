package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.util.UserInfoHolder
import com.fido.fcnetworksite.vo.MoodVo
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 14:33
 * @description:
 */
@RestController
@RequestMapping("/v1/mood")
@CrossOrigin
class MoodController {
    @Autowired
    private lateinit var moodService: MoodService

    @PostMapping()
    @ApiOperation(value = "发布心情")
    fun createMood(@RequestBody moodVo: MoodVo): DataMap {
        moodService.insertMood(MoodVo(content = moodVo.content, photoList = moodVo.photoList, userId = UserInfoHolder.userId))
        return ResponseBuilder.create().ok().build()
    }

    @DeleteMapping("/{moodId}")
    @ApiOperation(value = "删除心情")
    fun deleteMood(@PathVariable("moodId") moodId: Int): DataMap {
        moodService.deleteMood(UserInfoHolder.userId, moodId)
        return ResponseBuilder.create().ok().build()
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "分页获取心情")
    fun getMoodByUserId(@PathVariable userId: Long,
                        @RequestParam("pageIndex") pageIndex: Int,
                        @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().data(moodService.selectMoodByUserId(userId, pageIndex, pageSize)).build()
    }

    @GetMapping("/content")
    @ApiOperation(value = "模糊查询心情")
    fun getMoodLikeContent(@RequestParam("content") content: String,
                           @RequestParam("pageIndex") pageIndex: Int,
                           @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().data(moodService.selectMoodLikeContent(content, pageIndex, pageSize)).build()
    }

    /**
     * 用户点赞
     */
    @PostMapping("/like")
    fun likeMood(@RequestBody moodVo: MoodVo): DataMap {
        return ResponseBuilder.create().ok().build()
    }
}