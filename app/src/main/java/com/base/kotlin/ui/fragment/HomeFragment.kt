package com.base.kotlin.ui.fragment

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import butterknife.BindView
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.ui.contract.HomeFragmentContract
import com.base.kotlin.ui.presenter.HomeFragmentPresenter


class HomeFragment : BaseMvpFragment<HomeFragmentContract.Presenter>(), HomeFragmentContract.View{

    @BindView(R.id.navigation)
    lateinit var navigation: BottomNavigationView

    override fun bindPresenter(): HomeFragmentContract.Presenter {
        return HomeFragmentPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initClick() {

    }

    override fun initWidget(savedInstanceState: Bundle?) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(HeadlineFragment())
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {

            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                val fragment: Fragment
                when (item.itemId) {
                    R.id.navigation_top_headline -> {
                        setTitle(getString(R.string.navigation_top_headline))
                        fragment = HeadlineFragment()
                        loadFragment(fragment)
                        return true
                    }
                    R.id.navigation_news -> {
                        setTitle(getString(R.string.navigation_news))
                        fragment = NewsFragment()
                        loadFragment(fragment)
                        return true
                    }

                    R.id.navigation_profile -> {
                        setTitle(getString(R.string.navigation_profile))
                        fragment = ProfileFragment()
                        loadFragment(fragment)
                        return true
                    }
                }
                return false
            }
        }

    /**
     * replaces fragment - just use in homepage
     */
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}