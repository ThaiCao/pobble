package com.base.kotlin.core

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

open class BaseMvpPresenter < M : BaseMvpModel ,T : BaseContract.BaseView> : BaseContract.BasePresenter<T>{

    private var viewRef : WeakReference<T>? = null
    protected var mDisposable: CompositeDisposable? = null
    protected var model: M? = null

    constructor(view: T) {
        attachView(view)
        mDisposable = CompositeDisposable()
    }

    override fun attachView(view: T) {
        viewRef = WeakReference(view)

    }

    override fun detachView() {
        if(viewRef !=null){
            viewRef!!.clear()
            viewRef = null
        }
        if (mDisposable != null) {
            mDisposable!!.clear()
            mDisposable!!.dispose()
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