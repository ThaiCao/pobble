package com.base.kotlin.application

import android.content.Context
import android.os.Handler
import android.support.multidex.MultiDexApplication

class AppApplication: MultiDexApplication() {
    companion object{
        private var sContext: AppApplication? = null

        @Volatile public var  applicationHandler: Handler? = null

        public var sStartTime: Long = System.currentTimeMillis()

        public fun context(): AppApplication{
            return sContext!!
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        sContext = this
    }

    override fun onCreate() {
        super.onCreate()
        applicationHandler = Handler(applicationContext.mainLooper)
    }
}