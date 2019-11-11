package com.base.kotlin.core

abstract class BaseMvpFragment <T : BaseContract.BasePresenter<BaseContract.BaseView>> : BaseFragment(), BaseContract.BaseView{

    protected var mPresenter :T? = null

    protected abstract fun bindPresenter() : T

    override fun processLogic() {
        mPresenter = bindPresenter()
        if(mPresenter !=null){
            mPresenter!!.attachView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mPresenter !=null){
            mPresenter!!.detachView()
        }
    }

    override fun showError(message: String?) {

    }
}