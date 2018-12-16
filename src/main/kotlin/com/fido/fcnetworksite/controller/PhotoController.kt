package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.PhotoService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.util.UserInfoHolder
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/28 22:57
 * @description:
 */
@RestController
@RequestMapping("/v1/photo")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @PostMapping("/upload")
    @ApiOperation("图片上传")
    fun upload(@RequestParam(value = "photo") photo: MultipartFile): DataMap {
        return ResponseBuilder.create().ok().data(photoService.uploadPhotos(UserInfoHolder.userId, photo)).build()
    }
}