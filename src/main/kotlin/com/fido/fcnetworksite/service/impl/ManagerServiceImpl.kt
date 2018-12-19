package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.UserDao
import com.fido.fcnetworksite.service.ManagerService
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.PageInfoVo
import com.fido.fcnetworksite.vo.UserVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 12:15 2018/12/19
 */
@Service
class ManagerServiceImpl:ManagerService {
    @Autowired
    private lateinit var userService:UserService
    @Autowired
    private lateinit var moodService: MoodService
    override fun selectUser(state: Int, page: Int, pageSize: Int): PageInfoVo<UserVo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selectMood(state: Int, userName: String, content: String): PageInfoVo<MoodVo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun passUser(userId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun passMood(moodId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun freezeUser(userId: Long) {
        userService.freezeUser(userId)
    }

    override fun unFreezeUser(userId: Long) {
        userService.unFreezeUser(userId)
    }
}