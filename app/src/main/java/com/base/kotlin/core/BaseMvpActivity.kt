package com.base.kotlin.core

import android.content.Context
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseMvpActivity <T : BaseContract.BasePresenter< BaseContract.BaseView>> : BaseActivity(),
    BaseContract.BaseView {

    protected var mPresenter : T? = null

    protected abstract fun bindPresenter() : T

    override fun processLogic() {
        attacthView(bindPresenter())
    }

    private fun attacthView(presenter: T){
        mPresenter = presenter
        mPresenter!!.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.detachView()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}