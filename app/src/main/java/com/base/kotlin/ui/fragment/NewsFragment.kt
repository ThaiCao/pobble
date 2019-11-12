package com.base.kotlin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.base.kotlin.R
import com.base.kotlin.core.BaseListAdapter
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.data.bean.response.NewsResponse
import com.base.kotlin.data.bean.response.ResponseError
import com.base.kotlin.ui.adapter.FilterItemclickListener
import com.base.kotlin.ui.adapter.NewsAdapter
import com.base.kotlin.ui.contract.NewsFragmentContract
import com.base.kotlin.ui.dialog.NewsFilterDialogFragment
import com.base.kotlin.ui.presenter.NewsFragmentPresenter
import com.base.kotlin.widget.adapter.RecyclerViewDividerItemDecoration

class NewsFragment : BaseMvpFragment<NewsFragmentContract.Presenter>(), NewsFragmentContract.View , BaseListAdapter.onItemClickListener{

    lateinit var adapter: NewsAdapter
    @BindView(R.id.rcvNews)
    lateinit var rcvNews : RecyclerView

    @BindView(R.id.txtTitle)
    lateinit var txtTitle : TextView

    @BindView(R.id.tvNoData)
    lateinit var tvNoData : TextView

    override fun bindPresenter(): NewsFragmentContract.Presenter {
        return NewsFragmentPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initClick() {
        txtTitle.setOnClickListener {
            displayFilter()
        }
    }

    override fun initWidget(savedInstanceState: Bundle?) {
        txtTitle.text ="bitcoin"

        adapter = NewsAdapter()
        adapter.setOnItemClickListener(this)

        val layoutManager = LinearLayoutManager(view!!.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        if(rcvNews !=null){
            rcvNews.layoutManager = layoutManager
            rcvNews.addItemDecoration(RecyclerViewDividerItemDecoration(activity!!))
            rcvNews.adapter = adapter

        }
    }

    override fun processLogic() {
        super.processLogic()
        onLoadDatabyFilter("bitcoin")
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    private fun displayFilter(){
        val dialog = NewsFilterDialogFragment()
        dialog.setItemClick(listener = object :  FilterItemclickListener<String> {
            override fun onItemClick(data: String) {
                txtTitle.text = data
                onLoadDatabyFilter(data)
                dialog.dismiss()
            }

        })
        dialog.show(fragmentManager, "")
    }

    fun onLoadDatabyFilter(value: String){
        Log.e("TEST_DATA","NEWPAGE onLoadDatabyFilter: " + value)
        mPresenter!!.getNewsData(value,"2019-11-11","2019-11-11","popularity")
    }

    override fun onGetNewsError(error: ResponseError) {
        if(view !=null){
            tvNoData.visibility = View.VISIBLE
        }
    }

    override fun onGetNewsSuccess(data: NewsResponse) {
        if(view !=null && data!=null && data.articles !=null){
            tvNoData.visibility = View.GONE
            adapter.refreshItem(data.articles!!)
        }
    }

    override fun onItemClick(view: View, position: Int) {
        var articleFragment = ArticleFragment()
        var bundle :Bundle = Bundle()
        bundle.putParcelable(ArticleFragment.ARTICLE_ITEM, adapter.getItem(position))
        articleFragment.arguments = bundle
        pushFragment(articleFragment,true)
    }
}