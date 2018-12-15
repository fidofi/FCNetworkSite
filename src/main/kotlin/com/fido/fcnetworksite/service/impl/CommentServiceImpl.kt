package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.constant.PhotoConstant.DEFAULT_PHOTO_URL
import com.fido.fcnetworksite.dao.CommentDao
import com.fido.fcnetworksite.entity.CommentEntity
import com.fido.fcnetworksite.service.CommentService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.vo.CommentVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/3 16:00
 * @description:
 */
@Service
class CommentServiceImpl : CommentService {
    @Autowired
    private lateinit var commentDao: CommentDao
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var valueOperations:ValueOperations<String,Any>

    override fun insert(commentVo: CommentVo) {
        valueOperations.increment(commentVo.moodId.toString(),1)
        commentDao.insert(CommentEntity(commentVo.content, commentVo.moodId, commentVo.userId))

    }

    override fun select(moodId: Int): List<CommentVo> {
        val commentList = commentDao.select(moodId)
        if (commentList.isEmpty()) {
            return emptyList()
        }
        val userIdList = commentList.map { it.userId }
        val userInfoList=userService.batchSelectUser(userIdList)
        val userNameMap = userInfoList.map { it.userId to it.nickName }.toMap()
        val userPhotoMap=userInfoList.map { it.userId to it.photoUrl }.toMap()
        return commentDao.select(moodId).map { CommentVo(moodId, it.content, it.userId, it.commentId, userNameMap[it.userId]!!, it.createTime,userPhotoMap[it.userId]?:DEFAULT_PHOTO_URL) }
    }

    override fun delete(commentId: Int) {
        commentDao.delete(commentId)
    }

    override fun readComment(commentId: Int) {
        commentDao.readComment(commentId)
    }
}