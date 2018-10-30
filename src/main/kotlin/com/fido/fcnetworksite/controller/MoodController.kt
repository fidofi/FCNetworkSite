package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.resolver.JsonParam
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.util.ResponseBuilder
import org.apache.ibatis.annotations.Delete
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 14:33
 * @description:
 */
@RestController
@RequestMapping("/v1/mood")
class MoodController {
    @Autowired
    private lateinit var moodService: MoodService

    @PostMapping()
    fun createMood(@JsonParam("content") content: String,
                   @JsonParam("photoUrlList") photoUrlList: List<String>): DataMap {
        return ResponseBuilder.create().ok().build()
    }

    @Delete("/{moodId}")
    fun deleteMood(@PathVariable("moodId") moodId: Int): DataMap {
        return ResponseBuilder.create().ok().build()
    }

    @GetMapping("/{userId}")
    fun getMoodByUserId(@PathVariable userId: Long,
                        @RequestParam("pageIndex") pageIndex: Int,
                        @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().data(moodService.selectMoodByUserId(userId, pageIndex, pageSize)).build()
    }

    @GetMapping("/content")
    fun getMoodLikeContent(@RequestParam("content") content: String,
                           @RequestParam("pageIndex") pageIndex: Int,
                           @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().build()
    }


}