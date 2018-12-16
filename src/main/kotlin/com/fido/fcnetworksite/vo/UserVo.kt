package com.fido.fcnetworksite.vo

import com.fido.fcnetworksite.annotation.SaveUser
import com.fido.fcnetworksite.annotation.UpdateUser
import com.fido.fcnetworksite.constant.PhotoConstant.DEFAULT_PHOTO_URL
import com.fido.fcnetworksite.enum.SexEnum
import org.hibernate.validator.constraints.NotBlank
import java.time.LocalDate
import javax.validation.constraints.NotNull

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 16:40 2018/10/18
 */
class UserVo(@NotNull(message = "userId不能为空", groups = [UpdateUser::class]) val userId: Long = 0,
             @NotBlank(message = "email不能为空", groups = [SaveUser::class]) val email: String = "",
             @NotBlank(message = "nickName不能为空", groups = [SaveUser::class, UpdateUser::class]) val nickName: String = "",
             val sex: Int = SexEnum.MALE.code,
             @NotNull(message = "birthday不能为空", groups = [SaveUser::class, UpdateUser::class])
             val birthday: LocalDate = LocalDate.now(),
             val photoUrl: String = DEFAULT_PHOTO_URL,
             val introduction: String = "",
             @NotBlank(message = "password不能为空", groups = [SaveUser::class]) val password: String = ""
) {
    override fun toString(): String {
        return "UserVo(userId=$userId, email='$email', nickName='$nickName', sex=$sex, birthday=$birthday, photoUrl='$photoUrl', introduction='$introduction', password='$password')"
    }
}