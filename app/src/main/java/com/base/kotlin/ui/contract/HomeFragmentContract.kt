package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract

interface HomeFragmentContract {

    interface Model: BaseContract.BaseModel {

    }

    interface View : BaseContract.BaseView{
    }

    interface Presenter<View> : BaseContract.BasePresenter<BaseContract.BaseView>{
    }
}