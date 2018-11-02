package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.MoodDao
import com.fido.fcnetworksite.entity.MoodEntity
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.PageInfoVo
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jca.cci.core.support.CciDaoSupport
import org.springframework.stereotype.Service

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/30 22:58
 * @description:
 */
@Service
class MoodServiceImpl : MoodService {
    @Autowired
    private lateinit var moodDao: MoodDao

    override fun insertMood(moodVo: MoodVo) {
        moodDao.insertMood(MoodEntity(moodVo.content, moodVo.userId))
    }

    override fun selectMoodLikeContent(content: String, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateMoodInfo(moodId: Int, commentCount: Long, likeCount: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selectMoodByUserId(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo> {
        PageHelper.startPage<MoodEntity>(pageIndex, pageSize)
        val list = moodDao.selectMoodByUserId(1)
        val result = PageInfo(list)
        return PageInfoVo<MoodVo>(1, 1, 1, listOf(MoodVo(1, 1, "1", "1", 1, 1, listOf("1"))))
    }

    override fun deleteMood(moodId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}