package com.base.kotlin.data.api

import android.content.Context
import com.base.kotlin.BuildConfig
import com.base.kotlin.application.AppApplication
import com.base.kotlin.data.api.converter.GsonConverterFactory
import com.base.kotlin.data.api.converter.NullOnEmptyConverterFactory
import com.base.kotlin.data.api.intercepter.HeaderInterceptor
import com.base.kotlin.data.api.intercepter.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.util.concurrent.TimeUnit

class RequestClient {
    companion object{
        @Volatile private var sServerAPI: ServerAPI? = null
        private var sSharedInstance: RequestClient? = null

        @Synchronized public fun getSharedInstance(): RequestClient{
            if(sSharedInstance == null){
                sSharedInstance = RequestClient(AppApplication.context())
            }
            return sSharedInstance!!
        }
    }

    private var mContext: Context? = null

    constructor(mContext: Context?) {
        this.mContext = mContext
        getServerAPI()
    }

    public fun getServerAPI(): ServerAPI{
        if(sServerAPI == null){
            synchronized(RequestClient::class.java){
                if(sServerAPI == null){
                    val clientBuilder: OkHttpClient.Builder = getClientBuilder()
                    val headerMap: HashMap<String, String> = HashMap()
                    clientBuilder.addInterceptor(HeaderInterceptor(headerMap))
                    if(BuildConfig.DEBUG){
                        clientBuilder.addInterceptor(LoggingInterceptor())
                    }
                    sServerAPI = getRetrofitBuilder(BuildConfig.APP_HOST_URL , clientBuilder.build()).build().create(ServerAPI::class.java)
                }
            }
        }
        return sServerAPI!!
    }

    private fun getClientBuilder(): OkHttpClient.Builder{
        return OkHttpClient.Builder()
                .connectTimeout(HttpConfig.CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.READ_TIME_OUT_SECONDS, TimeUnit.SECONDS )
                .retryOnConnectionFailure(true)
    }

    private fun getRetrofitBuilder(url: String, client: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(client)
    }


}