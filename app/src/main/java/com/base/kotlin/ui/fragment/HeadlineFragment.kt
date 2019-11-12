package com.base.kotlin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.base.kotlin.R
import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseListAdapter
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.data.bean.response.GetHeadlineResponse
import com.base.kotlin.data.bean.response.ResponseError
import com.base.kotlin.ui.adapter.HeadlineAdapter
import com.base.kotlin.ui.contract.HeadlineFragmentContract
import com.base.kotlin.ui.presenter.HeadlineFragmentPresenter
import com.base.kotlin.widget.adapter.RecyclerViewDividerItemDecoration

class HeadlineFragment : BaseMvpFragment<HeadlineFragmentContract.Presenter>(), HeadlineFragmentContract.View, BaseListAdapter.onItemClickListener{

    lateinit var adapter: HeadlineAdapter

    @BindView(R.id.rcvHeadline)
    lateinit var rcvHeadline : RecyclerView

    @BindView(R.id.tvNoData)
    lateinit var tvNoData : TextView

    override fun bindPresenter(): HeadlineFragmentContract.Presenter {
        return HeadlineFragmentPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_headline
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initClick() {

    }

    override fun initWidget(savedInstanceState: Bundle?) {
        adapter = HeadlineAdapter()
        adapter.setOnItemClickListener(this)

        val layoutManager = LinearLayoutManager(view!!.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        if(rcvHeadline !=null){
            rcvHeadline.layoutManager = layoutManager
            rcvHeadline.addItemDecoration(RecyclerViewDividerItemDecoration(activity!!))
            rcvHeadline.adapter = adapter

        }
    }

    override fun processLogic() {
        super.processLogic()
        mPresenter!!.getHeadlineData("us","business")
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onGetHeadlineSuccess(data: GetHeadlineResponse) {
        if(view !=null && data!=null && data.articles !=null){
            tvNoData.visibility = View.GONE
            adapter.refreshItem(data.articles!!)
        }
    }

    override fun onGetHeadlineError(error: ResponseError) {
        if(view !=null){
            tvNoData.visibility = View.VISIBLE
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