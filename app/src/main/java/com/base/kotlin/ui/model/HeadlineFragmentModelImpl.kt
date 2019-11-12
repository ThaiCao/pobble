package com.base.kotlin.ui.model

import com.base.kotlin.core.BaseMvpModel
import com.base.kotlin.data.api.RequestClient
import com.base.kotlin.data.bean.response.BaseResponse
import com.base.kotlin.data.bean.response.GetHeadlineResponse
import com.base.kotlin.ui.contract.HeadlineFragmentContract
import io.reactivex.Observable

class HeadlineFragmentModelImpl : BaseMvpModel(), HeadlineFragmentContract.Model {
    override fun executeGetHeadline(country: String, category: String): Observable<GetHeadlineResponse> {
        return RequestClient.getSharedInstance().getHeadlineData(country, category)
    }
}