package com.base.kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.Toolbar
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpActivity
import com.base.kotlin.ui.contract.HomeActivityContract
import com.base.kotlin.ui.fragment.HomeFragment
import com.base.kotlin.ui.presenter.HomeActivityPresenter
import android.support.design.widget.BottomNavigationView


open class HomeActivity: BaseMvpActivity<HomeActivityContract.Presenter<HomeActivityContract.View>>(), HomeActivityContract.View {

    lateinit var toolbar: ActionBar

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(message: String?) {

    }

    override fun initFragment() {
        pushFragment(HomeFragment(), true, true, true)
    }

    override fun bindPresenter(): HomeActivityContract.Presenter<HomeActivityContract.View> {
        return HomeActivityPresenter()
    }

    override fun setupToolbar(toolbar: Toolbar) {

    }

    override fun activityLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun layoutContainerId(): Int {
        return R.id.container
    }

    override fun initWidget() {
        toolbar = this!!.supportActionBar!!
    }


    override fun initClick() {

    }

    override fun initToolbar() {

    }

    override fun setTitle(title: String) {
        toolbar.title = title
    }

    override fun displayBackButtonToolbar(isDisplay: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

}