package com.base.kotlin.ui.fragment

import android.os.Bundle
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.ui.contract.HomeFragmentContract
import com.base.kotlin.ui.presenter.HomeFragmentPresenter

class HomeFragment : BaseMvpFragment<HomeFragmentContract.Presenter<HomeFragmentContract.View>>(), HomeFragmentContract.View{


    override fun bindPresenter(): HomeFragmentContract.Presenter<HomeFragmentContract.View> {
        return HomeFragmentPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initClick() {

    }

    override fun initWidget(savedInstanceState: Bundle?) {

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun processLogic() {
        super.processLogic()

    }
}