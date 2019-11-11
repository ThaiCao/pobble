package com.base.kotlin.widget.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.base.kotlin.R
import com.base.kotlin.core.BaseListAdapter
import java.util.*

abstract class WholeAdapter<T>: BaseListAdapter<T> {
    private val TAG = "WholeAdapter"
    private val TYPE_ITEM = 0

    //Refresh class
    private lateinit var mLoadDelegate: LoadMoreDelegate

    private val mHeaderList = ArrayList<ItemView>(2)
    private val mFooterList = ArrayList<ItemView>(2)

//    constructor() : super()

    constructor(context: Context, options: Options?){
        if (options != null) {
            mLoadDelegate = LoadMoreDelegate(context, options)
            mFooterList.add(mLoadDelegate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            super.onCreateViewHolder(parent, viewType)
        } else {
            createOtherViewHolder(parent, viewType)
        }
    }

    private fun createOtherViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        for (i in mHeaderList.indices) {
            val itemView = mHeaderList[i]
            if (viewType == itemView.hashCode()) {
                view = itemView.onCreateView(parent)
            }
        }
        for (i in mFooterList.indices) {
            val itemView = mFooterList[i]
            if (viewType == itemView.hashCode()) {
                view = itemView.onCreateView(parent)
            }
        }
        return object : RecyclerView.ViewHolder(view!!) {

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < mHeaderList.size) {
            mHeaderList[position].onBindView(holder.itemView)
        } else if (position < mHeaderList.size + getItemSize()) {
            super.onBindViewHolder(holder, position - mHeaderList.size)
        } else {
            val pos = position - mHeaderList.size - getItemSize()
            mFooterList[pos].onBindView(holder.itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var type = 0
        if (position < mHeaderList.size) {
            type = mHeaderList[position].hashCode()
        } else if (position < mHeaderList.size + getItemSize()) {
            type = TYPE_ITEM
        } else {
            val pos = position - mHeaderList.size - getItemSize()
            type = mFooterList[pos].hashCode()
        }
        return type
    }

    override fun getItemCount(): Int {
        return mHeaderList.size + getItemSize() + mFooterList.size
    }

    fun addHeaderView(itemView: ItemView) {
        mHeaderList.add(itemView)
    }

    fun addFooterView(itemView: ItemView) {
        if (mLoadDelegate != null) {
            val count = mFooterList.size - 1
            mFooterList.add(count, itemView)
        } else {
            mFooterList.add(itemView)
        }
    }

    override fun addItems(values: List<T>) {
        if (values.isEmpty() && mLoadDelegate != null) {
            mLoadDelegate!!.setLoadMoreStatus(LoadMoreView.TYPE_NO_MORE)
        }
        super.addItems(values)
    }

    fun setOnLoadMoreListener(listener: LoadMoreView.OnLoadMoreListener) {
        checkLoadMoreExist()
        mLoadDelegate!!.setOnLoadMoreListener(listener)
    }

    private fun checkLoadMoreExist() {
        if (mLoadDelegate == null)
            throw IllegalArgumentException("you must setting LoadMore Option")
    }

    fun refreshItems(list: List<T>) {
        if (mLoadDelegate != null) {
            mLoadDelegate!!.setLoadMoreStatus(LoadMoreView.TYPE_LOAD_MORE)
        }
        super.refreshItem(list)
    }

    fun showLoadError() {
        //Set to load error
        checkLoadMoreExist()
        mLoadDelegate!!.setLoadMoreStatus(LoadMoreView.TYPE_LOAD_ERROR)
        notifyDataSetChanged()
    }


    //Set the case when GridLayout
    internal inner class WholeGridSpanSizeLookUp(maxSize: Int) : GridLayoutManager.SpanSizeLookup() {
        var maxSize = 1

        init {
            this.maxSize = maxSize
        }

        override fun getSpanSize(position: Int): Int {
            if (position < mHeaderList.size) {
                return maxSize
            }
            return if (position < mHeaderList.size + getItemSize()) {
                1
            } else {
                maxSize
            }
        }
    }

    class Options {
        @LayoutRes
        var loadMoreId = R.layout.view_load_more
        @LayoutRes
        var errorId = R.layout.view_error
        @LayoutRes
        var noMoreId = R.layout.view_nomore
    }

    interface ItemView {
        fun onCreateView(parent: ViewGroup): View
        fun onBindView(view: View)
    }
}