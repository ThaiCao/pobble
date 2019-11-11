package com.base.kotlin.ui.presenter

import com.base.kotlin.R
import com.base.kotlin.application.AppApplication
import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.data.api.RequestClient
import com.base.kotlin.ui.contract.HomeFragmentContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentPresenter: BaseMvpPresenter<BaseContract.BaseView>(), HomeFragmentContract.Presenter<HomeFragmentContract.View> {

}