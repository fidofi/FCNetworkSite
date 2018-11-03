package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.PhotoDao
import com.fido.fcnetworksite.entity.PhotoEntity
import com.fido.fcnetworksite.service.PhotoService
import com.fido.fcnetworksite.vo.PhotoVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 8:56 2018/11/3
 */
@Service
class PhotoServiceImpl : PhotoService {
    @Autowired
    private lateinit var photoDao: PhotoDao

    override fun uplodePhotos(): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun batchInsert(moodId: Int, list: List<PhotoVo>) {
        photoDao.insert(list.map { PhotoEntity(moodId, it.photoUrl, it.order) })
    }

    override fun select(moodId: Int): List<PhotoVo> {
        return photoDao.select(moodId).map { PhotoVo(it.photoUrl, it.order) }
    }

    override fun batchSelectByMoodId(moodId: Int): Map<Int, List<PhotoVo>> {
        val photoList = photoDao.batchSelectByMoodId(moodId)
        val result = mutableMapOf<Int, List<PhotoVo>>()
        photoList.groupBy { it.moodId }.forEach { moodId, photoList -> result[moodId] = photoList.map { PhotoVo(it.photoUrl, it.order) } }
        return result
    }
}

