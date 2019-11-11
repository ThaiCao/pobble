package com.base.kotlin.core

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

public abstract class BaseFragment : Fragment() {

    // main layout of Fragment
    private lateinit var rootView : View

    // unbinder to bind or unbind of ButterKnife
    private lateinit var unbinder : Unbinder

    @LayoutRes
    protected abstract fun getLayoutId(): Int


    /************************** init area *********************/
    /**
     * init data
     * savedInstanceState : Bundle
     */
    protected abstract fun initData(savedInstanceState: Bundle?)

    /**
     * init click
     */
    protected abstract fun initClick()

    /**
     * handle logic
     */
    protected abstract fun processLogic()

    /**
     * init widget
     */
    protected abstract fun initWidget( savedInstanceState : Bundle?)

    /************************** lifecycle area *********************/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        var resId : Int = getLayoutId()
        rootView = inflater.inflate(resId, container, false)
        unbinder = ButterKnife.bind(this, rootView)
        return  rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData(savedInstanceState)
        initWidget(savedInstanceState)
        initClick()
        processLogic()
    }

    override fun onDetach() {
        super.onDetach()
        unbinder.unbind()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    /************************** base function area *********************/

    public fun getName(): String{
        return javaClass.name
    }

    protected fun <VT : View?> getViewById(id: Int) : VT? {
        return rootView!!.findViewById<VT>(id)
    }

    fun pushFragment(fragment: BaseFragment, addBackStack: Boolean) {
        pushFragment(fragment, addBackStack, true)
    }

    fun pushFragment(fragment: BaseFragment, addBackStack: Boolean, animation: Boolean) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).pushFragment(fragment, false, addBackStack, animation)
        }
    }

    fun pushFragment(fragment: BaseFragment, clearStack: Boolean, addBackStack: Boolean, animation: Boolean) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).pushFragment(fragment, clearStack, addBackStack, animation)
        }
    }
}