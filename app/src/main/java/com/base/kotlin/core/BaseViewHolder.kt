package com.base.kotlin.core

import android.support.v7.widget.RecyclerView
import android.view.View

public class BaseViewHolder <T> : RecyclerView.ViewHolder{

    public var holder: IViewHolder<T>? = null

    constructor(itemView: View, holder: IViewHolder<T>) : super(itemView){
        this.holder = holder
        holder.initview()
    }
}