package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.ui.contract.HomeActivityContract
import com.base.kotlin.ui.model.HomeActivityModelImpl

class HomeActivityPresenter: BaseMvpPresenter<HomeActivityModelImpl, BaseContract.BaseView>, HomeActivityContract.Presenter {

    constructor(view: HomeActivityContract.View) : super(view){
        model = HomeActivityModelImpl()
    }
}