package com.fido.fcnetworksite.util;

import com.fido.fcnetworksite.constant.PrefixConstant;
import com.fido.fcnetworksite.vo.UserVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/12/17 21:35
 * @description:
 */
public class UserInfoUtils {

    public static UserVo getUserVo() {
        HttpServletRequest request =
                ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
                        .getRequest();
        HttpSession session = request.getSession();
        return (UserVo) session.getAttribute((PrefixConstant.SESSION_INFO_PREFIX));
    }

    public static void setUserVo(UserVo userVo) {
        HttpServletRequest request =
                ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
                        .getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(PrefixConstant.SESSION_INFO_PREFIX, userVo);
    }
}
