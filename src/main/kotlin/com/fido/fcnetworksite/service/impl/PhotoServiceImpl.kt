package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.constant.PhotoConstant.IP
import com.fido.fcnetworksite.constant.PhotoConstant.PASSWORD
import com.fido.fcnetworksite.constant.PhotoConstant.PHOTO_URL_PREFIX
import com.fido.fcnetworksite.constant.PhotoConstant.USERNAME
import com.fido.fcnetworksite.dao.PhotoDao
import com.fido.fcnetworksite.entity.PhotoEntity
import com.fido.fcnetworksite.service.PhotoService
import com.fido.fcnetworksite.util.PhotoUtils
import com.fido.fcnetworksite.vo.PhotoVo
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 8:56 2018/11/3
 */
@Service
class PhotoServiceImpl : PhotoService {
    @Autowired
    private lateinit var photoDao: PhotoDao

    override fun uploadPhotos(photo: MultipartFile): List<String> {
        // 1. 创建一个FtpClient对象
        val ftpClient = FTPClient()
        // 2. 创建 ftp 连接
        ftpClient.connect(IP, 21)
        // 3. 登录 ftp 服务器
        ftpClient.login(USERNAME, PASSWORD)
        // 4. 读取文件
        val inputStream = photo.inputStream
        // 5. 设置上传的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images")
        // 6. 修改上传文件的格式为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE)
        // 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
        ftpClient.enterLocalPassiveMode()
        val photoName = PhotoUtils.getName()
        ftpClient.storeFile(photoName, inputStream)
        // 8. 关闭连接
        ftpClient.logout()
        return listOf(PHOTO_URL_PREFIX + photoName)
    }

    override fun batchInsert(moodId: Int, list: List<String>) {
        photoDao.insert(list.mapIndexed { index, s -> PhotoEntity(moodId, s, index + 1) })
    }

    override fun select(moodId: Int): List<PhotoVo> {
        return photoDao.select(moodId).map { PhotoVo(it.photoUrl, it.order) }
    }

    override fun batchSelectByMoodId(moodIdList: List<Int>): Map<Int, List<String>> {
        if (moodIdList.isNotEmpty()) {
            val photoList = photoDao.batchSelectByMoodId(moodIdList)
            val result = mutableMapOf<Int, List<String>>()
            photoList.groupBy { it.moodId }.forEach { moodId, photoList -> result[moodId] = photoList.sortedBy { it.order }.map { it.photoUrl } }
            return result
        } else {
            return emptyMap()
        }
    }
}

