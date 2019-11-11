package com.base.kotlin.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

public abstract class ViewHolderImpl<T> : IViewHolder<T> {
    private var view: View? = null
    private var context: Context? = null

    protected abstract fun getItemLayoutId() : Int

    override fun createItemView(parent: ViewGroup): View {
        view = LayoutInflater.from(parent.context).inflate(getItemLayoutId(), parent, false)
        ButterKnife.bind(this, view!!)
        context = parent.context
        return view!!
    }

    protected  fun <V: View> findViewBindId(id: Int) : V? {
        return view?.findViewById(id)
    }

    protected fun getContext(): Context{
        return context!!
    }

    protected fun getItemView(): View{
        return view!!
    }

    override fun onClick() {

    }
}