package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.vo.CommentVo

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/3 15:54
 * @description:
 */
interface CommentService {
    fun insert(commentVo: CommentVo)

    fun select(moodId: Int): List<CommentVo>

    fun delete(commentId: Int)

    fun readComment(commentId: Int)
}