package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.ui.contract.ProfileFragmentContract
import com.base.kotlin.ui.model.ProfileFragmentModelImpl

class ProfileFragmentPresenter: BaseMvpPresenter<ProfileFragmentModelImpl, BaseContract.BaseView>, ProfileFragmentContract.Presenter {
    constructor(view: ProfileFragmentContract.View) : super(view){
        model = ProfileFragmentModelImpl();
    }
}