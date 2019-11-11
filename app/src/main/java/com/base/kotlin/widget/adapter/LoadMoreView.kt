package com.base.kotlin.widget.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

class LoadMoreView: FrameLayout {

    companion object{
        private val TAG = "LoadMoreView"
        val TYPE_HIDE = 0
        val TYPE_LOAD_MORE = 1
        val TYPE_NO_MORE = 2
        val TYPE_LOAD_ERROR = 3
    }

    private var mLoadMoreView: View? = null
    private var mErrorView: View? = null
    private var mNoMoreView: View? = null

    private var mListener: OnLoadMoreListener? = null

    private var mStatus = TYPE_HIDE

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(
        context: Context, @LayoutRes loadMoreId: Int,
        @LayoutRes errorId: Int, @LayoutRes noMoreId: Int) : super(context) {
        initView(loadMoreId, errorId, noMoreId)
    }

    private fun initView(loadMoreId: Int, errorId: Int, noMoreId: Int) {
        mLoadMoreView = inflateId(loadMoreId)
        mErrorView = inflateId(errorId)
        mNoMoreView = inflateId(noMoreId)

        addView(mLoadMoreView)
        addView(mErrorView)
        addView(mNoMoreView)

        refreshView()

        mErrorView!!.setOnClickListener { v -> setLoadMore() }
    }

    private fun inflateId(id: Int): View {
        return LayoutInflater.from(context)
            .inflate(id, this, false)
    }

    fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
        mListener = listener
    }

    fun refreshView() {
        when (mStatus) {
            TYPE_HIDE -> setHide()
            TYPE_LOAD_MORE -> setLoadMore()
            TYPE_NO_MORE -> setLoadNoMore()
            TYPE_LOAD_ERROR -> setLoadError()
        }
    }

    fun setLoadMoreStatus(status: Int) {
        mStatus = status
        refreshView()
    }

    private fun setHide() {
        mLoadMoreView!!.visibility = View.GONE
        mErrorView!!.visibility = View.GONE
        mNoMoreView!!.visibility = View.GONE
    }

    private fun setLoadMore() {
        //Download Data
        mLoadMoreView!!.visibility = View.VISIBLE
        mErrorView!!.visibility = View.GONE
        mNoMoreView!!.visibility = View.GONE
        //load
        if (mListener != null) {
            mListener!!.onLoadMore()
        }
    }

    private fun setLoadError() {
        mLoadMoreView!!.visibility = View.GONE
        mErrorView!!.visibility = View.VISIBLE
        mNoMoreView!!.visibility = View.GONE
    }

    private fun setLoadNoMore() {
        mLoadMoreView!!.visibility = View.GONE
        mErrorView!!.visibility = View.GONE
        mNoMoreView!!.visibility = View.VISIBLE
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}