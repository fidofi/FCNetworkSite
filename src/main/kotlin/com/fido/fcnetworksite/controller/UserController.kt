package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.annotation.SaveUser
import com.fido.fcnetworksite.annotation.UpdateUser
import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.constant.PrefixConstant
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.exception.BaseException
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.util.UserInfoHolder
import com.fido.fcnetworksite.util.ValidatorUtils
import com.fido.fcnetworksite.vo.LoginVo
import com.fido.fcnetworksite.vo.UpdatePasswordVo
import com.fido.fcnetworksite.vo.UserVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 22:57
 * @description:
 */
@RestController
@RequestMapping("/v1/user")
@Api(description = "用户相关的接口")
@CrossOrigin
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @ApiOperation(value = "用户注册", notes = "用户注册必填参数：email,nickName,password,introduction,其他不传的话有默认值")
    @PostMapping("/register")
    fun saveUser(@Validated(SaveUser::class) @RequestBody userVo: UserVo, result: BindingResult): DataMap {
        userService.saveUser(userVo)
        if (result.hasErrors()) {
            throw BaseException(StatusEnum.REQUEST_PARAMS_NOT_VALID, ValidatorUtils.buildErrorMessage(result))
        }
        return ResponseBuilder.create().ok().build()
    }

    @ApiOperation(value = "用户登录", notes = "用户登录填写邮件和密码，会有对应的错误提示")
    @ApiImplicitParams(ApiImplicitParam(dataType = "String", name = "email", value = "邮件地址", required = true),
            ApiImplicitParam(dataType = "String", name = "password", value = "密码", required = true))
    @PostMapping("/login")
    fun login(@RequestBody loginVo: LoginVo,
              request: HttpServletRequest): DataMap {
        userService.login(loginVo.email, loginVo.password)
        request.session.setAttribute(PrefixConstant.SESSION_INFO_PREFIX, UserInfoHolder.userInfo)
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
    fun updatePassword(@RequestBody updatePasswordVo: UpdatePasswordVo): DataMap {
        userService.updateUserPassword(updatePasswordVo.email, updatePasswordVo.newPassword, updatePasswordVo.oldPassword)
        return ResponseBuilder.create().ok().build()
    }

    @ApiOperation(value = "根据昵称获取用户列表")
    @GetMapping("/find-user/{nickName}")
    fun findUserLikeName(@PathVariable("nickName") nickName: String): DataMap {
        return ResponseBuilder.create().ok().data(userService.selectUserLikeName(nickName)).build()
    }

}