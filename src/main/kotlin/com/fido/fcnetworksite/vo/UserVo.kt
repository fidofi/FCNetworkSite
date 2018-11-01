package com.fido.fcnetworksite.vo

import com.fido.fcnetworksite.annotation.SaveUser
import com.fido.fcnetworksite.annotation.UpdateUser
import com.fido.fcnetworksite.enum.SexEnum
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 16:40 2018/10/18
 */
class UserVo(@NotNull(message = "userId不能为空", groups = [UpdateUser::class]) val userId: Long,
             @NotBlank(message = "email不能为空", groups = [SaveUser::class, UpdateUser::class]) val email: String,
             @NotBlank(message = "nickName不能为空", groups = [SaveUser::class, UpdateUser::class]) val nickName: String,
             val sex: Int = SexEnum.MALE.code,
             @NotNull(message = "birthday不能为空", groups = [SaveUser::class, UpdateUser::class])
             val birthday: LocalDate = LocalDate.now(),
             val photoUrl: String?,
             @NotBlank(message = "password不能为空", groups = [SaveUser::class]) val password: String = ""
)