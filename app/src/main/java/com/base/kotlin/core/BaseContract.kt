package com.base.kotlin.core

interface BaseContract {
    interface BaseModel{}

    interface  BasePresenter<T : BaseView>{

        fun attachView(view : T)

        fun detachView()

        fun start()

        fun destroy()
    }

    interface BaseView{

        fun showLoading()

        fun hideLoading()

        fun showError(message : String?)
    }
}