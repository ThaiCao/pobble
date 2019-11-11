package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.ui.contract.ProfileFragmentContract

class ProfileFragmentPresenter: BaseMvpPresenter<BaseContract.BaseView>(), ProfileFragmentContract.Presenter<ProfileFragmentContract.View> {

}