package com.base.kotlin.data.bean.response

/**
 * Created by thai.cao on 11/12/2019.
 */
class ResponseError {
    lateinit var errorKind : Throwable

    constructor(errorKind: Throwable) {
        this.errorKind = errorKind
    }
}