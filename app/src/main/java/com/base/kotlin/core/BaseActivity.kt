package com.base.kotlin.core

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Toolbar
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseActivity : AppCompatActivity(){

    // unbinder to bind or unbind of ButterKnife
    private var unbinder : Unbinder? = null

    protected var dialogMessage : AlertDialog? = null

    /**
     * setup toolbar for each fragment
     */
    protected abstract fun setupToolbar(toolbar: Toolbar)

    /**
     * get Layout Id in Res
     */
    protected abstract fun activityLayoutId() : Int

    /**
     * return Layout container parent, it will contain fragment
     */
    protected abstract fun layoutContainerId() : Int

    /**
     * init widget : view, textview, button, layout,... text size, text color... after loadView in onCreate
     */
    protected abstract fun initWidget()

    /**
     * init click event
     */
    protected abstract fun initClick()

    /**
     * init toolbar
     */
    protected abstract fun initToolbar()

    /**
     * handle App logic
     */
    protected abstract fun processLogic()

    /**
     * init Data
     */
    protected abstract fun initData(savedInstanceState : Bundle?)


    /**
     * set title
     */
    abstract fun setTitle(title : String)

    /**
     * set visible or invisible back button in toolbar
     */
    abstract fun displayBackButtonToolbar(isDisplay : Boolean)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // support actionbar
        if(supportActionBar !=null){
            supportActionBar!!.elevation = 0f
        }
        // load layout
        setContentView(activityLayoutId())
        // init ButterKinife
        unbinder = ButterKnife.bind(this)
        // init data
        initData(savedInstanceState)
        // init toolbar
        initToolbar()
        // init widget
        initWidget()
        // init click
        initClick()
        // handle logic data
        processLogic()
    }

    /**
     * clear all fragments
     */
    protected fun clearAllFragments(){
        while(supportFragmentManager.backStackEntryCount >0){
            supportFragmentManager.popBackStackImmediate()
        }
    }

    /**
     * replace old fragment by new fragment
     */
    public fun replaceFragment(fragment : BaseFragment, addToBack : Boolean, animation: Boolean){
        var idContainer : Int = layoutContainerId()
        Log.e("TEST_APP","replaceFragment  idContainer $idContainer")
        if(idContainer <=0){
            return
        }
        var ft :FragmentTransaction = supportFragmentManager.beginTransaction()
        // replace fragment with or without animation
        if(animation){
//            ft.setCustomAnimations()
        }
        ft.replace(idContainer, fragment, fragment.getName())
        // add to back state or no
        if(addToBack){
            ft.addToBackStack(fragment.getName())
        }
        ft.commitAllowingStateLoss()
        Log.e("TEST_APP","replaceFragment  finish")
    }

    /**
     * push a new fragment
     */
    public fun pushFragment(fragment: BaseFragment, clearStack: Boolean, addToBack: Boolean, animation: Boolean){
        // clear all fragments if true
        if(clearStack){
            clearAllFragments()
        }

        replaceFragment(fragment, addToBack, animation)
    }

    /**
     * get current fragment
     */
    protected fun getCurrentFragment() : Fragment?{
        var layoutContainer : Int = layoutContainerId()
        if(layoutContainer <=0){
            return null
        }
        return supportFragmentManager.findFragmentById(layoutContainer)
    }

    /**
     * check dialog message is showing or not
     */
    public fun isDialogMessageShowing() : Boolean{
        if(dialogMessage !=null && dialogMessage!!.isShowing){
            return  true
        }
        return  false
    }

    public fun showDialogMessage(title: String, message: String) {
        if (isDialogMessageShowing()) {
            return
        }

        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            dialogMessage = null
        }

        builder.setOnDismissListener {
            dialogMessage = null
        }
        dialogMessage = builder.show()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}