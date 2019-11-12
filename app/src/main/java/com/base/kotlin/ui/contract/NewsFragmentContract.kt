package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract
import com.base.kotlin.data.bean.response.NewsResponse
import com.base.kotlin.data.bean.response.ResponseError
import io.reactivex.Observable

interface NewsFragmentContract {

    interface Model: BaseContract.BaseModel {
        fun executeGetNews(keyword: String, from: String, to: String, sortBy: String): Observable<NewsResponse>
    }

    interface View : BaseContract.BaseView{
        fun onGetNewsSuccess(data: NewsResponse)
        fun onGetNewsError(error: ResponseError)
    }

    interface Presenter : BaseContract.BasePresenter<BaseContract.BaseView>{
        fun getNewsData(keyword: String, from: String, to: String, sortBy: String)
    }
}