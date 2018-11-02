package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.entity.CommentEntity
import org.apache.ibatis.annotations.Param

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:35 2018/11/2
 */
interface CommentDao {
    fun select(@Param("moodId") moodId: Int): List<CommentEntity>

    fun insert(commentEntity: CommentEntity)

    fun delete(@Param("commentId") commentId: Int)
}