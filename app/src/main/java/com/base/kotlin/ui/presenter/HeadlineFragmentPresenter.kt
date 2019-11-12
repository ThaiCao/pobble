package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.data.bean.response.ResponseError
import com.base.kotlin.ui.contract.HeadlineFragmentContract
import com.base.kotlin.ui.model.HeadlineFragmentModelImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HeadlineFragmentPresenter: BaseMvpPresenter<HeadlineFragmentModelImpl, BaseContract.BaseView>,
    HeadlineFragmentContract.Presenter {
    var view :HeadlineFragmentContract.View

    constructor(view: HeadlineFragmentContract.View) : super(view){
        model = HeadlineFragmentModelImpl()
        this.view = view
    }


    override fun getHeadlineData(country: String, category: String) {
        val disposable = model!!.executeGetHeadline(country, category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> view.onGetHeadlineSuccess(response) },
                { throwable -> view.onGetHeadlineError(ResponseError(throwable)) }
            )
        mDisposable!!.add(disposable)
    }

}