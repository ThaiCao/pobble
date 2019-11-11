package com.base.kotlin.core

import java.lang.ref.WeakReference

open class BaseMvpPresenter <T : BaseContract.BaseView> : BaseContract.BasePresenter<T>{

    private var viewRef : WeakReference<T>? = null


    override fun attachView(view: T) {
        viewRef = WeakReference<T>(view)
    }

    override fun detachView() {
        if(viewRef !=null){
            viewRef!!.clear()
            viewRef = null
        }

    }

    override fun start() {

    }

    override fun destroy() {
        if(isViewAttached()){
            detachView()
        }
    }

    public final fun isViewAttached() : Boolean{
        return  viewRef !=null && viewRef!!.get() !=null
    }

    public fun getView(): T{
        return viewRef?.get()!!
    }
}