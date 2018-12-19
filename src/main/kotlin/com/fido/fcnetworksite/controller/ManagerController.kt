package com.fido.fcnetworksite.controller

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.service.ManagerService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.vo.LoginVo
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.UserVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    @Autowired
    private lateinit var managerService: ManagerService

    @PostMapping("/login")
    fun login(@RequestBody loginVo: LoginVo): DataMap {
        val userVo = userService.login(loginVo.email, loginVo.password)
        return ResponseBuilder.create().ok().data(userVo).build()
    }

    @GetMapping("/search-user")
    fun searchUser(@RequestParam(value = "userName", required = false) userName: String?,
                   @RequestParam("state") state: Int,
                   @RequestParam("page") page: Int,
                   @RequestParam("pageSize") pageSize: Int): DataMap {
        val result = managerService.selectUser(state, userName, page, pageSize)
        return ResponseBuilder.create().ok().data(result).build()
    }

    @GetMapping("/search-mood")
    fun searchMood(@RequestParam("state") state: Int,
                   @RequestParam(value = "content", required = false) content: String?,
                   @RequestParam(value = "userName", required = false) userName: String?,
                   @RequestParam(value = "page") page: Int,
                   @RequestParam(value = "pageSize") pageSize: Int): DataMap {
        val result = managerService.selectMood(state, userName, content, page, pageSize)
        return ResponseBuilder.create().ok().data(result).build()
    }

    @PostMapping("/freeze-user")
    fun freezeUser(@RequestBody userVo: UserVo): DataMap {
        managerService.freezeUser(userVo.userId)
        return ResponseBuilder.create().ok().build()
    }

    @PostMapping("/unfreeze-user")
    fun unFreezeUser(@RequestBody userVo: UserVo): DataMap {
        managerService.unFreezeUser(userVo.userId)
        return ResponseBuilder.create().ok().build()
    }

    @PostMapping("/pass-user")
    fun passUser(@RequestBody userVo: UserVo): DataMap {
        managerService.passUser(userVo.userId)
        return ResponseBuilder.create().ok().build()
    }

    @PostMapping("/reject-user")
    fun rejectUser(@RequestBody userVo: UserVo): DataMap {
        managerService.unPassUser(userVo.userId)
        return ResponseBuilder.create().ok().build()
    }

    @PostMapping("/pass-mood")
    fun passMood(@RequestBody moodVo: MoodVo): DataMap {
        managerService.passMood(moodVo.moodId)
        return ResponseBuilder.create().ok().build()
    }

    @PostMapping("reject-mood")
    fun rejectMood(@RequestBody moodVo: MoodVo): DataMap {
        managerService.unPassMood(moodVo.moodId)
        return ResponseBuilder.create().ok().build()
    }

}