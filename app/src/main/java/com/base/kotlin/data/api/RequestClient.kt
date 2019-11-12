package com.base.kotlin.data.api

import android.content.Context
import com.base.kotlin.BuildConfig
import com.base.kotlin.application.AppApplication
import com.base.kotlin.data.api.converter.GsonConverterFactory
import com.base.kotlin.data.api.converter.NullOnEmptyConverterFactory
import com.base.kotlin.data.api.intercepter.HeaderInterceptor
import com.base.kotlin.data.api.intercepter.LoggingInterceptor
import com.base.kotlin.data.bean.response.GetHeadlineResponse
import com.base.kotlin.data.bean.response.NewsResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.security.cert.CertificateException
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


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
                    val clientBuilder: OkHttpClient.Builder = getUnsafeOkHttpClient()
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

//    private fun getClientBuilder(): OkHttpClient.Builder{
////        return OkHttpClient.Builder()
////                .connectTimeout(HttpConfig.CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
////                .readTimeout(HttpConfig.READ_TIME_OUT_SECONDS, TimeUnit.SECONDS )
////                .retryOnConnectionFailure(true)
//
//    }


    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun getRetrofitBuilder(url: String, client: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
    }


    fun getHeadlineData( country: String, category: String): Observable<GetHeadlineResponse> {
        return sServerAPI!!.getHeadlineData(country, category, BuildConfig.APP_API_KEY)
    }

    fun getNewsData( key: String, from: String,to: String, sortBy: String): Observable<NewsResponse> {
        return sServerAPI!!.getNewsData(key, from, to, sortBy, BuildConfig.APP_API_KEY)
    }
}