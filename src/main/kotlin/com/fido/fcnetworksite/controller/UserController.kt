package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.annotation.SaveUser
import com.fido.fcnetworksite.annotation.UpdateUser
import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.vo.UserVo
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

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

    @ApiOperation(value = "用户注册")
    @PostMapping
    fun saveUser(@Validated(SaveUser::class) @RequestBody userVo: UserVo): DataMap {
        userService.saveUser(userVo)
        return ResponseBuilder.create().ok().build()
    }

    @ApiOperation(value = "更改用户基本信息")
    @PutMapping("/base-info")
    fun updateUserInfo(@RequestBody @Validated(UpdateUser::class) userVo: UserVo): DataMap {
        userService.updateUserInfo(userVo)
        return ResponseBuilder.create().ok().build()
    }

    @ApiOperation(value = "更改用户密码")
    @PutMapping("/password")
    fun updatePassword(@RequestParam("email") email: String,
                       @RequestParam("oldPassword") oldPassword: String,
                       @RequestParam("newPassword") newPassword: String): DataMap {
        userService.updateUserPassword(email, newPassword, oldPassword)
        return ResponseBuilder.create().ok().build()
    }

    @ApiOperation(value = "根据昵称获取用户列表")
    @GetMapping("/find-user/{nickName}")
    fun findUserLikeName(@PathVariable("nickName") nickName: String): DataMap {
        return ResponseBuilder.create().ok().data(userService.selectUserLikeName(nickName)).build()
    }

}