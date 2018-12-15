package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.CommentService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.util.UserInfoHolder
import com.fido.fcnetworksite.vo.CommentVo
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 15:11
 * @description:
 */
@RestController
@RequestMapping("/v1/comment")
@CrossOrigin
class CommentController {
    @Autowired
    private lateinit var commentService: CommentService

    @PostMapping
    @ApiOperation(value = "发表评论")
    fun insertComment(@RequestBody comment: CommentVo): DataMap {
        commentService.insert(CommentVo(comment.moodId, comment.content, UserInfoHolder.userVo.userId))
        return ResponseBuilder.create().ok().build()
    }

    @GetMapping("/{moodId}")
    @ApiOperation(value = "获取评论列表")
    fun selectCommentList(@PathVariable("moodId") moodId: Int): DataMap {
        return ResponseBuilder.create().ok().data(commentService.select(moodId)).build()
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "删除评论")
    fun deleteComment(@PathVariable("commentId") commentId: Int): DataMap {
        commentService.delete(commentId)
        return ResponseBuilder.create().ok().build()
    }
}