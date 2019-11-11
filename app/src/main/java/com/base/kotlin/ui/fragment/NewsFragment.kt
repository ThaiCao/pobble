package com.base.kotlin.ui.fragment

import android.os.Bundle
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.ui.contract.NewsFragmentContract
import com.base.kotlin.ui.presenter.NewsFragmentPresenter

class NewsFragment : BaseMvpFragment<NewsFragmentContract.Presenter<NewsFragmentContract.View>>(), NewsFragmentContract.View{

    override fun bindPresenter(): NewsFragmentContract.Presenter<NewsFragmentContract.View> {
        return NewsFragmentPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
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