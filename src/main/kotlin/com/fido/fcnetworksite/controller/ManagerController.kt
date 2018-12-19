package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.vo.LoginVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 11:24 2018/12/19
 */
@RestController
@RequestMapping("/v1/manager")
class ManagerController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/login")
    fun login(@RequestBody loginVo: LoginVo): DataMap {
        val userVo = userService.login(loginVo.email, loginVo.password)
        return ResponseBuilder.create().ok().data(userVo).build()
    }
}