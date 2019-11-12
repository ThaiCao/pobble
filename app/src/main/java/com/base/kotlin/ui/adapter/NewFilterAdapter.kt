package com.base.kotlin.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.base.kotlin.R
import com.base.kotlin.application.AppApplication
import com.base.kotlin.core.BaseListAdapter
import com.base.kotlin.core.IViewHolder
import com.base.kotlin.core.ViewHolderImpl
import com.base.kotlin.data.bean.model.Articles
import com.bumptech.glide.Glide

/**
 * Created by thai.cao on 11/12/2019.
 */
class NewFilterAdapter : BaseListAdapter<String>(){


    override fun createViewHolder(viewType: Int): IViewHolder<String> {
        return HeadlineHolder()
    }



    internal class HeadlineHolder : ViewHolderImpl<String>() {


        @BindView(R.id.txtTitle)
        lateinit var txtTitle: TextView


        override fun getItemLayoutId(): Int {
            return  R.layout.item_new_filter
        }

        override fun initview() {

        }

        override fun onBind(data: String?, position: Int) {
            if(data !=null){
                txtTitle.text = data

            }
        }
        override fun onClick() {
            super.onClick()

        }
    }

}