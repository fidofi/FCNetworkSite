package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.constant.PrefixConstant
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.util.UserInfoHolder
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.UserVo
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

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

    private val logger = LoggerFactory.getLogger(MoodController::class.java)
    @PostMapping()
    @ApiOperation(value = "发布心情")
    fun createMood(@RequestBody moodVo: MoodVo, request: HttpServletRequest): DataMap {
        val userVo = request.session.getAttribute(PrefixConstant.SESSION_INFO_PREFIX) as UserVo
        logger.info("uservo" + userVo)
        moodService.insertMood(MoodVo(content = moodVo.content, photoList = moodVo.photoList, userId = userVo.userId))
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
        moodService.likeMood(UserInfoHolder.userId, moodVo.moodId)
        return ResponseBuilder.create().ok().build()
    }

    @GetMapping("/popular")
    fun getPopular(@RequestParam("pageIndex") pageIndex: Int,
                   @RequestParam("pageSize") pageSize: Int): DataMap {
        return ResponseBuilder.create().ok().data(moodService.selectPopularMoodList(pageIndex, pageSize)).build()
    }
}