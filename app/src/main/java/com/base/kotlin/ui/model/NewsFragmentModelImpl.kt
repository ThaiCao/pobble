package com.base.kotlin.ui.model

import com.base.kotlin.core.BaseMvpModel
import com.base.kotlin.data.api.RequestClient
import com.base.kotlin.data.bean.response.BaseResponse
import com.base.kotlin.data.bean.response.NewsResponse
import com.base.kotlin.ui.contract.NewsFragmentContract
import io.reactivex.Observable

class NewsFragmentModelImpl : BaseMvpModel(), NewsFragmentContract.Model {
    override fun executeGetNews(keyword: String, from: String, to: String, sortBy: String): Observable<NewsResponse> {
        return RequestClient.getSharedInstance().getNewsData(keyword, from, to, sortBy)
    }
}