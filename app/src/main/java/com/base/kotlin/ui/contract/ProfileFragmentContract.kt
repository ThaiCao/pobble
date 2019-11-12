package com.base.kotlin.ui.contract

import com.base.kotlin.core.BaseContract
import com.base.kotlin.data.bean.model.UserProfile
import com.base.kotlin.data.bean.response.ResponseError
import io.reactivex.Observable

interface ProfileFragmentContract {

    interface Model: BaseContract.BaseModel {
        fun executeGetProfile(): Observable<UserProfile>
        fun executeSaveProfile(userName: String, displayName: String, phone: String): Observable<UserProfile>
    }

    interface View : BaseContract.BaseView{
        fun onGetProfileSuccess(data: UserProfile)
        fun onGetProfileError(error: ResponseError)

        fun onSaveProfileSuccess(data: UserProfile)
        fun onSaveProfileError(error: ResponseError)
    }

    interface Presenter: BaseContract.BasePresenter<BaseContract.BaseView>{
        fun getProfileData()
        fun saveProfileData(userName: String, displayName: String, phone: String)
    }
}