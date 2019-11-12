package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.data.bean.response.ResponseError
import com.base.kotlin.ui.contract.NewsFragmentContract
import com.base.kotlin.ui.model.NewsFragmentModelImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsFragmentPresenter: BaseMvpPresenter< NewsFragmentModelImpl, BaseContract.BaseView>, NewsFragmentContract.Presenter {
    var view :NewsFragmentContract.View
    constructor(view: NewsFragmentContract.View) : super(view){
        model = NewsFragmentModelImpl()
        this.view = view
    }

    override fun getNewsData(keyword: String, from: String, to: String, sortBy: String) {
        val disposable = model!!.executeGetNews(keyword, from, to, sortBy)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> view.onGetNewsSuccess(response) },
                { throwable -> view.onGetNewsError(ResponseError(throwable)) }
            )
        mDisposable!!.add(disposable)
    }
}