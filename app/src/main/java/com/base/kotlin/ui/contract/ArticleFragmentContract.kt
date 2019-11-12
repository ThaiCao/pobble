package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract

interface ArticleFragmentContract {

    interface Model: BaseContract.BaseModel {

    }

    interface View : BaseContract.BaseView{
    }

    interface Presenter : BaseContract.BasePresenter<BaseContract.BaseView>{
    }
}