package com.base.kotlin.ui.presenter

import com.base.kotlin.core.BaseContract
import com.base.kotlin.core.BaseMvpPresenter
import com.base.kotlin.ui.contract.ArticleFragmentContract
import com.base.kotlin.ui.model.ArticleFragmentModelImpl

class ArticleFragmentPresenter: BaseMvpPresenter<ArticleFragmentModelImpl, BaseContract.BaseView>, ArticleFragmentContract.Presenter {
    constructor(view: ArticleFragmentContract.View) : super(view){
        model = ArticleFragmentModelImpl()
    }
}