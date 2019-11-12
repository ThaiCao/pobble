package com.base.kotlin.ui.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.base.kotlin.R
import com.base.kotlin.application.AppApplication
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.data.bean.model.Articles
import com.base.kotlin.ui.contract.ArticleFragmentContract
import com.base.kotlin.ui.presenter.ArticleFragmentPresenter
import com.bumptech.glide.Glide

class ArticleFragment : BaseMvpFragment<ArticleFragmentContract.Presenter>(), ArticleFragmentContract.View{

    companion object{
        val ARTICLE_ITEM: String ="ARTICLE_ITEM"
    }

    lateinit var article: Articles

    @BindView(R.id.txtTitle)
    lateinit var txtTitle: TextView

    @BindView(R.id.ivArticle)
    lateinit var ivArticle: ImageView

    @BindView(R.id.txtOriginalLink)
    lateinit var txtOriginalLink: TextView

    @BindView(R.id.txtContent)
    lateinit var txtContent: TextView


    override fun bindPresenter(): ArticleFragmentContract.Presenter {
        return ArticleFragmentPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article
    }

    override fun initData(savedInstanceState: Bundle?) {
        if(arguments!=null){
            if(arguments!!.containsKey(ARTICLE_ITEM)){
                article = arguments!!.getParcelable(ARTICLE_ITEM)
            }
        }
    }

    override fun initClick() {

    }

    override fun initWidget(savedInstanceState: Bundle?) {
        if(article !=null){
            txtTitle.text = article.title
            txtContent.text = article.content
            Glide.with(AppApplication.context())
                .load(article.urlToImage)
                .into(ivArticle)
            txtOriginalLink.text = article.url
        }
        txtOriginalLink.movementMethod = LinkMovementMethod.getInstance()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}