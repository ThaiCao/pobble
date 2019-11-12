package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.data.bean.response.ResponseError
import com.base.kotlin.ui.contract.ProfileFragmentContract
import com.base.kotlin.ui.model.ProfileFragmentModelImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileFragmentPresenter: BaseMvpPresenter<ProfileFragmentModelImpl, BaseContract.BaseView>, ProfileFragmentContract.Presenter {

    var view :ProfileFragmentContract.View
    constructor(view: ProfileFragmentContract.View) : super(view){
        model = ProfileFragmentModelImpl()
        this.view = view
    }

    override fun getProfileData() {
        val disposable = model!!.executeGetProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> view.onGetProfileSuccess(response) },
                { throwable -> view.onGetProfileError(ResponseError(throwable)) }
            )
        mDisposable!!.add(disposable)
    }

    override fun saveProfileData(userName: String, displayName: String, phone: String) {
        val disposable = model!!.executeSaveProfile(userName, displayName, phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> view.onSaveProfileSuccess(response) },
                { throwable -> view.onSaveProfileError(ResponseError(throwable)) }
            )
        mDisposable!!.add(disposable)
    }

}