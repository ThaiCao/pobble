package com.base.kotlin.data.api

import com.base.kotlin.data.bean.response.GetHeadlineResponse
import com.base.kotlin.data.bean.response.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ServerAPI {
    companion object  {
        const val GET_HEADLINE = "top-headlines"
        const val GET_NEWS = "everything"
    }

    @GET(GET_HEADLINE)
    fun getHeadlineData(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String) : Observable<GetHeadlineResponse>

    @GET(GET_NEWS)
    fun getNewsData(@Query("q") keyword: String, @Query("from") from: String, @Query("to") to: String, @Query("sortBy") sortBy: String, @Query("apiKey") apiKey: String) : Observable<NewsResponse>
}