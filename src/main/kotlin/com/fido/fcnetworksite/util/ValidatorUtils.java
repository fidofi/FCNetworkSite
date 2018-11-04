package com.fido.fcnetworksite.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/4 14:53
 * @description:
 */
public class ValidatorUtils {

    private ValidatorUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取验证结果中的错误提示信息
     *
     * @param result : 验证结果
     * @return String : 提示信息字符串
     */
    public static String buildErrorMessage(BindingResult result) {
        StringBuilder message = new StringBuilder();
        List<ObjectError> list = result.getAllErrors();
        if (list != null && !list.isEmpty()) {
            for (ObjectError elem : list) {
                String defaultMessage = elem.getDefaultMessage();
                if (StringUtils.isNotEmpty(defaultMessage)) {
                    message.append(defaultMessage).append("  ");
                }
            }
        }
        return message.toString();
    }
}
