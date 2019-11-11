package com.base.kotlin.core

import android.app.Dialog
import android.content.Context

public abstract class BaseMvpDialog<T: BaseContract.BasePresenter<BaseContract.BaseView>> : Dialog, BaseContract.BaseView {

    protected var mPresenter: T? = null

    protected abstract fun bindPresenter(): T

    constructor(context: Context, themeResId: Int) : super(context, themeResId){
        mPresenter = bindPresenter()
        if(mPresenter !=null){
            mPresenter?.attachView(this)
        }
    }

    override fun dismiss() {
        super.dismiss()
        if(mPresenter !=null){
            mPresenter?.detachView()
        }
    }
}