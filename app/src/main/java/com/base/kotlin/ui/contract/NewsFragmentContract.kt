package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract

interface NewsFragmentContract {

    interface Model: BaseContract.BaseModel {

    }

    interface View : BaseContract.BaseView{
    }

    interface Presenter<View> : BaseContract.BasePresenter<BaseContract.BaseView>{
    }
}