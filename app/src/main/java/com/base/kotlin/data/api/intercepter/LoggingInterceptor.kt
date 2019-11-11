package com.base.kotlin.data.api.intercepter

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class LoggingInterceptor: Interceptor {

    companion object{
        private val SINGLE_DIVIDER: String = "────────────────────────────────────────────"
        private val TAG: String = "LoggingInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var nanoTime: Long = System.nanoTime()


        var response: Response = chain.proceed(request)
        var responseBody: ResponseBody? = response.body() ?: return response
        var bodyString: String = responseBody!!.string()

        return response.newBuilder()
            .body(ResponseBody.create(response.body()!!.contentType(), bodyString))
            .build()
    }
}