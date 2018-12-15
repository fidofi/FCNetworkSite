package com.fido.fcnetworksite.vo

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 18:32 2018/11/2
 */
class PhotoVo {
    var photoUrl: String = ""
    var order: Int = 1

    constructor()
    constructor(photoUrl: String, order: Int) {
        this.photoUrl = photoUrl
        this.order = order
    }


}