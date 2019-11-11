package com.base.kotlin.widget.adapter

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import com.base.kotlin.R
import android.graphics.drawable.Drawable



class RecyclerViewDividerItemDecoration: RecyclerView.ItemDecoration {
    private lateinit var mDivider: Drawable

    constructor(context: Context ) : super() {
        this.mDivider = context.resources.getDrawable(R.drawable.recyclerview_devider_line)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}