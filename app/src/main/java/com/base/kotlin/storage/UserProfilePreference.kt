package com.base.kotlin.storage

import android.content.Context
import com.base.kotlin.application.AppApplication
import com.base.kotlin.data.bean.model.UserProfile
import com.google.gson.Gson

/**
 * Created by thai.cao on 11/12/2019.
 */
class UserProfilePreference {

    private val USER_PROFILE_PREFRENCE_KEY = "user_profile_preference"
    private val VERIFY_PHONE_PREFRENCE_KEY = "verify_phone_preference"
    private val USER_PROFILE_KEY = "user_profile"


    companion object{
        private val lock = Any()
        private var mInstance: UserProfilePreference? = null

        fun getInstance(): UserProfilePreference {
            synchronized(lock) {
                if (mInstance == null) {
                    mInstance = UserProfilePreference()
                }
            }
            return mInstance!!
        }
    }

    fun saveUserProfile(userProfile: UserProfile) {
        val context = AppApplication.context()
        if (context != null) {
            // Store in preference
            val editor = context!!.getSharedPreferences(
                USER_PROFILE_PREFRENCE_KEY, Context.MODE_PRIVATE
            ).edit()
            editor.putString(USER_PROFILE_KEY, Gson().toJson(userProfile))
            editor.commit()
        }
    }

    fun clearUserProfile() {
        val context = AppApplication.context()
        if (context != null) {
            // Store in preference
            val editor = context!!.getSharedPreferences(
                USER_PROFILE_PREFRENCE_KEY, Context.MODE_PRIVATE
            ).edit()
            editor.putString(USER_PROFILE_KEY, "")
            editor.commit()
        }
    }

    fun getUserProfile(): UserProfile {
        var mUserProfile: UserProfile? = null
        val context = AppApplication.context()
        if (context != null) {
            val lSharePreference = context!!.getSharedPreferences(
                USER_PROFILE_PREFRENCE_KEY, Context.MODE_PRIVATE
            )
            val userProfileString = lSharePreference.getString(
                USER_PROFILE_KEY, ""
            )
            if (userProfileString != null && userProfileString!!.isNotEmpty()) {
                mUserProfile = Gson().fromJson(userProfileString, UserProfile::class.java)
            }
        }
        if(mUserProfile == null){
            mUserProfile = UserProfile("","","")
        }
        return mUserProfile!!
    }
}