package com.base.kotlin.core

import android.view.View
import android.view.ViewGroup

public interface IViewHolder <T>{

    // create View of item
    fun createItemView(parent: ViewGroup) : View

    // init view, layout
    fun initview()

    // on bind data to view
    fun onBind(data: T?, position: Int)

    // on handle click event
    fun onClick()
}