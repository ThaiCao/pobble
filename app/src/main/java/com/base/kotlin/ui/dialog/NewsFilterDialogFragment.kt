package com.base.kotlin.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.base.kotlin.R
import com.base.kotlin.core.BaseListAdapter
import com.base.kotlin.ui.adapter.FilterItemclickListener
import com.base.kotlin.ui.adapter.NewFilterAdapter
import com.base.kotlin.widget.adapter.RecyclerViewDividerItemDecoration
import java.util.ArrayList

/**
 * Created by thai.cao on 11/12/2019.
 */
class NewsFilterDialogFragment: DialogFragment(), BaseListAdapter.onItemClickListener {


    private var unbinder: Unbinder? = null
    private var newFilter = ArrayList<String>()
    lateinit var adapter : NewFilterAdapter
    private lateinit var listener: FilterItemclickListener<String>

    @BindView(R.id.rcvNewsFiler)
    lateinit var rcvNewsFiler : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_filter_news, null)

        unbinder = ButterKnife.bind(this, view)
        init()

        return view
    }

    @SuppressLint("ResourceType")
    fun init(){
//        newFilter  = arrayListOf(resources.getStringArray(R.array.filter_new_array).toString())
        initDataFilter()
        adapter = NewFilterAdapter()
        adapter.refreshItem(newFilter)
        adapter.setOnItemClickListener(this)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        if(rcvNewsFiler !=null){
            rcvNewsFiler.layoutManager = layoutManager
            rcvNewsFiler.addItemDecoration(RecyclerViewDividerItemDecoration(activity!!))
            rcvNewsFiler.adapter = adapter

        }
    }

    fun initDataFilter(){
        newFilter.add("bitcoin")
        newFilter.add("apple")
        newFilter.add("earthquake")
        newFilter.add("animal")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(unbinder !=null){
            unbinder!!.unbind()
        }
    }

    override fun onItemClick(view: View, position: Int) {
        listener.onItemClick(newFilter.get(position))
    }

    fun setItemClick(listener: FilterItemclickListener<String>) {
        this.listener = listener
    }
}