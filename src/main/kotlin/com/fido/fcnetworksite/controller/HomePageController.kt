package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.HomePageService
import com.fido.fcnetworksite.util.ResponseBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 8:59 2018/11/12
 */
@RestController
@RequestMapping("/v1/home-page")
@CrossOrigin
class HomePageController {
    @Autowired
    private lateinit var homePageService: HomePageService

    @GetMapping("/{userId}")
    fun getHomePage(@PathVariable("userId") userId: Long): DataMap {
        return ResponseBuilder.create().ok().data(homePageService.getHomePage(userId)).build()
    }
}