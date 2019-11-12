package com.base.kotlin.ui.fragment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import com.base.kotlin.R
import com.base.kotlin.core.BaseMvpFragment
import com.base.kotlin.data.bean.model.UserProfile
import com.base.kotlin.data.bean.response.ResponseError
import com.base.kotlin.ui.activity.HomeActivity
import com.base.kotlin.ui.contract.ProfileFragmentContract
import com.base.kotlin.ui.presenter.ProfileFragmentPresenter

class ProfileFragment : BaseMvpFragment<ProfileFragmentContract.Presenter>(), ProfileFragmentContract.View{


    @BindView(R.id.edtName)
    lateinit var edtName : EditText

    @BindView(R.id.edtDisplayName)
    lateinit var edtDisplayName : EditText

    @BindView(R.id.edtPhone)
    lateinit var edtPhone : EditText

    @BindView(R.id.btnSave)
    lateinit var btnSave : Button


    override fun bindPresenter(): ProfileFragmentContract.Presenter {
        return ProfileFragmentPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initClick() {
        btnSave.setOnClickListener {
            if(checkValidData()){
                showLoading()
                mPresenter!!.saveProfileData(edtName.text.toString(), edtDisplayName.text.toString(), edtPhone.text.toString())
            }else{
                Toast.makeText(activity,"Please input all data", Toast.LENGTH_LONG)
            }
        }
    }

    private fun checkValidData() : Boolean{
        if(edtName.text.toString().isNullOrEmpty()){
            return false
        }
        if(edtDisplayName.text.toString().isNullOrEmpty()){
            return false
        }
        if(edtPhone.text.toString().isNullOrEmpty()){
            return false
        }
        return true
    }

    override fun initWidget(savedInstanceState: Bundle?) {

    }

    override fun processLogic() {
        super.processLogic()
        showLoading()
        mPresenter!!.getProfileData()
    }

    override fun showLoading() {
        if(activity is HomeActivity){
            (activity as HomeActivity).showLoading()
        }
    }

    override fun hideLoading() {
        if(activity is HomeActivity){
            (activity as HomeActivity).hideLoading()
        }
    }

    override fun onGetProfileSuccess(data: UserProfile) {
        hideLoading()
        if(data ==null && data.userName.isNullOrEmpty()){

        }else{
            edtName.setText(data.userName)
            edtDisplayName.setText(data.displayName)
            edtPhone.setText(data.phone)

        }
    }

    override fun onGetProfileError(error: ResponseError) {
        hideLoading()
        Toast.makeText(activity,"Get profile fail", Toast.LENGTH_LONG)
    }

    override fun onSaveProfileSuccess(data: UserProfile) {
        hideLoading()
        Toast.makeText(activity,"Save profile success", Toast.LENGTH_LONG)
    }

    override fun onSaveProfileError(error: ResponseError) {
        hideLoading()
        Toast.makeText(activity,"Save profile fail", Toast.LENGTH_LONG)
    }
}