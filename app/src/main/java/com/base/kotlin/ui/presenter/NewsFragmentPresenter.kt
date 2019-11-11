package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.ui.contract.NewsFragmentContract

class NewsFragmentPresenter: BaseMvpPresenter<BaseContract.BaseView>(), NewsFragmentContract.Presenter<NewsFragmentContract.View> {

}