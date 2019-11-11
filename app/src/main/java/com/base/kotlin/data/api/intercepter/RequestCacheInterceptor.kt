package com.base.kotlin.data.api.intercepter

import com.base.kotlin.application.AppApplication
import com.base.kotlin.utils.SystemToolUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestCacheInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if(!SystemToolUtil.getInstance().checkNet(AppApplication.context())){
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }

        var originalResponse: Response = chain.proceed(request)
        if(SystemToolUtil.getInstance().checkNet(AppApplication.context())){
            var cacheControl: String = request.cacheControl().toString()
            return originalResponse.newBuilder()
                .header("Cache-Control",cacheControl)
                .removeHeader("Pragma")
                .build()
        }else{
            return originalResponse.newBuilder()
                .header("Cache-Control","public, only-if-cached, max-stale=2419200")
                .removeHeader("Pragma")
                .build()
        }
    }
}