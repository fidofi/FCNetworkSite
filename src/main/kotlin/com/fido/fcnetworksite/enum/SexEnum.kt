package com.fido.fcnetworksite.enum

/**
 * @author: fido
 * @desription:
 * @date: Created in 10:48 2018/10/12
 */
enum class SexEnum(val value: Char, val code: Int) {
    MALE('男', 0),
    FEMALE('女', 1);

    companion object {
        fun getSexEnumByCode(code: Int): SexEnum {
            SexEnum.values().forEach {
                if (code == it.code) {
                    return it
                }
            }
            return MALE
        }

    }

}
