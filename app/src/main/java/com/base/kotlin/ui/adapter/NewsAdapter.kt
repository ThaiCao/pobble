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
class NewsAdapter : BaseListAdapter<Articles>(){
    override fun createViewHolder(viewType: Int): IViewHolder<Articles> {
        return HeadlineHolder()
    }

    internal class HeadlineHolder : ViewHolderImpl<Articles>() {

        @BindView(R.id.ivThumb)
        lateinit var ivThumb: ImageView

        @BindView(R.id.txtTitle)
        lateinit var txtTitle: TextView

        @BindView(R.id.txtTime)
        lateinit var txtTime: TextView


        override fun getItemLayoutId(): Int {
            return  R.layout.item_category
        }

        override fun initview() {

        }

        override fun onBind(data: Articles?, position: Int) {
            if(data !=null){
                if(data.publishedAt !=null){
                    txtTime.text = data?.publishedAt
                }

                if(data.urlToImage !=null){
                    Glide.with(AppApplication.context())
                        .load(data.urlToImage)
                        .into(ivThumb)
                }
                if(data.title!= null){
                    txtTitle.text = data.title
                }
                getItemView().tag = data

            }
        }
        override fun onClick() {
            super.onClick()

        }
    }

}