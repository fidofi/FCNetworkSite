package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.RelationDao
import com.fido.fcnetworksite.service.RelationService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.vo.PageInfoVo
import com.fido.fcnetworksite.vo.UserBaseInfoVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:19 2018/11/8
 */

@Service
class RelationServiceImpl : RelationService {
    @Autowired
    private lateinit var relationDao: RelationDao
    @Autowired
    private lateinit var userService: UserService

    override fun followSomeOne(followingId: Long, followedId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unFollowSomeOne(followingId: Long, followedId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFans(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFollowingList(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}