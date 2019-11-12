package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.ui.contract.HomeFragmentContract
import com.base.kotlin.ui.model.HomeFragmentModelImpl

class HomeFragmentPresenter: BaseMvpPresenter<HomeFragmentModelImpl, BaseContract.BaseView>, HomeFragmentContract.Presenter {
    constructor(view: HomeFragmentContract.View) : super(view){
        model = HomeFragmentModelImpl()
    }
}