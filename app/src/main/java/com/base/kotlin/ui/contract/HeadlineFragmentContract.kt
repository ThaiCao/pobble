package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract
import com.base.kotlin.data.bean.response.GetHeadlineResponse
import com.base.kotlin.data.bean.response.ResponseError
import io.reactivex.Observable

interface HeadlineFragmentContract {

    interface Model: BaseContract.BaseModel {
        fun executeGetHeadline(country: String, category: String): Observable<GetHeadlineResponse>
    }

    interface View : BaseContract.BaseView{
        fun onGetHeadlineSuccess(data: GetHeadlineResponse)
        fun onGetHeadlineError(error: ResponseError)
    }

    interface Presenter : BaseContract.BasePresenter<BaseContract.BaseView>{
        fun getHeadlineData(country: String, category: String)
    }
}