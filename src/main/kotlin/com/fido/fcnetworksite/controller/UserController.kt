package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 22:57
 * @description:
 */
@RestController
@RequestMapping("/v1/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService

}