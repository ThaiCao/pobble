package com.base.kotlin.utils

import android.content.Context
import android.net.ConnectivityManager

class SystemToolUtil {

    companion object{
        private var sInstance: SystemToolUtil? = null
        fun getInstance(): SystemToolUtil {
            if (sInstance == null) {
                synchronized(SystemToolUtil.javaClass) {
                    if (sInstance == null) {
                        sInstance = SystemToolUtil()
                    }
                }
            }
            return sInstance!!
        }
    }

    private val TAG = "SystemToolUtil"

    /**
     * Determine if the network is connected
     */
    fun checkNet(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm!!.activeNetworkInfo
        return info != null// Whether the network is connected
    }


}