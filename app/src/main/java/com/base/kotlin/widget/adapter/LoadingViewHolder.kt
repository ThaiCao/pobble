package com.base.kotlin.widget.adapter

import com.base.kotlin.R
import com.base.kotlin.core.ViewHolderImpl

class LoadingViewHolder<T>:  ViewHolderImpl<T>() {


//    private var mView: View? = null
//
//    constructor(mView: View?)  {
//        this.mView = mView
//    }

    override fun initview() {

    }

    override fun onBind(data: T?, position: Int) {

    }

    override fun getItemLayoutId(): Int {
        return R.layout.view_load_more
    }

}