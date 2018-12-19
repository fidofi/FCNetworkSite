package com.fido.fcnetworksite.service.impl

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
class ManagerServiceImpl : ManagerService {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var moodService: MoodService

    override fun selectUser(state: Int, userName: String?, page: Int, pageSize: Int): PageInfoVo<UserVo> {
        return userService.selectUserByCondition(state, userName, page, pageSize)

    }

    override fun selectMood(state: Int, userName: String?, content: String?, page: Int, pageSize: Int): PageInfoVo<MoodVo> {
        return moodService.selectMoodByCondition(state, userName, content, page, pageSize)
    }

    override fun passUser(userId: Long) {
        userService.passUser(userId)
    }

    override fun unPassUser(userId: Long) {
        userService.reject(userId)
    }

    override fun unPassMood(moodId: Int) {
        moodService.unPassMood(moodId)
    }

    override fun passMood(moodId: Int) {
        moodService.passMood(moodId)
    }

    override fun freezeUser(userId: Long) {
        userService.freezeUser(userId)
    }

    override fun unFreezeUser(userId: Long) {
        userService.unFreezeUser(userId)
    }
}