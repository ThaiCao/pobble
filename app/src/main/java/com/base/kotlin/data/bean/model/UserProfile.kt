package com.base.kotlin.data.bean.model

/**
 * Created by thai.cao on 11/12/2019.
 */
class UserProfile {

    var userName: String? = null
    var displayName: String? = null
    var phone: String? = null

    constructor(userName: String?, displayName: String?, phone: String?) {
        this.userName = userName
        this.displayName = displayName
        this.phone = phone
    }
}