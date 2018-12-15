package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.vo.PhotoVo
import org.springframework.web.multipart.MultipartFile

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 13:12 2018/10/30
 */
interface PhotoService {
    fun uploadPhotos(userId: Long, photo: MultipartFile): List<String>

    fun batchInsert(moodId: Int, list: List<String>)

    fun select(moodId: Int): List<PhotoVo>

    fun batchSelectByMoodId(moodIdList: List<Int>): Map<Int, List<String>>
}