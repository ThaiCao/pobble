package com.base.kotlin.ui.fragment

import android.os.Bundle
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.ui.contract.HeadlineFragmentContract
import com.base.kotlin.ui.presenter.HeadlineFragmentPresenter

class HeadlineFragment : BaseMvpFragment<HeadlineFragmentContract.Presenter<HeadlineFragmentContract.View>>(), HeadlineFragmentContract.View{

    override fun bindPresenter(): HeadlineFragmentContract.Presenter<HeadlineFragmentContract.View> {
        return HeadlineFragmentPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_headline
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

}