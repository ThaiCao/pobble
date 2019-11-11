package com.base.kotlin.ui.fragment

import android.os.Bundle
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.ui.contract.ProfileFragmentContract
import com.base.kotlin.ui.presenter.ProfileFragmentPresenter

class ProfileFragment : BaseMvpFragment<ProfileFragmentContract.Presenter<ProfileFragmentContract.View>>(), ProfileFragmentContract.View{

    override fun bindPresenter(): ProfileFragmentContract.Presenter<ProfileFragmentContract.View> {
        return ProfileFragmentPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
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