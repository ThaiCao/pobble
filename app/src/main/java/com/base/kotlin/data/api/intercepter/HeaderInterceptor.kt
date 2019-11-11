package com.base.kotlin.data.api.intercepter

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    private var mHeader: Map<String, String>? = null

    constructor(mHeader: Map<String, String>?) {
        this.mHeader = mHeader
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        var builder: Request.Builder = chain.request().newBuilder()
        for(key: String in mHeader!!.keys){
            builder.addHeader(key, mHeader!!.get(key))
        }
        return chain.proceed(builder.build())
    }
}