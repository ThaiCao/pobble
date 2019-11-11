package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract

interface HomeActivityContract {

    interface Model: BaseContract.BaseModel {

    }

   interface View: BaseContract.BaseView {

         fun initFragment()

     }

   interface Presenter<View> : BaseContract.BasePresenter<BaseContract.BaseView> {

    }
}