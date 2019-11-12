package com.base.kotlin.ui.model

import com.base.kotlin.core.BaseMvpModel
import com.base.kotlin.data.bean.model.UserProfile
import com.base.kotlin.storage.UserProfilePreference
import com.base.kotlin.ui.contract.ProfileFragmentContract
import io.reactivex.Observable

class ProfileFragmentModelImpl : BaseMvpModel(), ProfileFragmentContract.Model {
    override fun executeSaveProfile(userName: String, displayName: String, phone: String): Observable<UserProfile> {
        var profile = UserProfile(userName, displayName, phone)
        if(profile !=null){
            UserProfilePreference.getInstance().saveUserProfile(profile)
        }else{
            profile = UserProfile("","","")
        }
        return Observable.just(profile)
    }

    override fun executeGetProfile(): Observable<UserProfile> {
        var profile = UserProfilePreference.getInstance().getUserProfile()
        if(profile == null || profile.userName.isNullOrEmpty()){
            profile = UserProfile("","","")
        }
        return Observable.just(profile)
    }
}