package com.base.kotlin.data.bean.response

import com.base.kotlin.data.bean.model.Articles
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by thai.cao on 11/12/2019.
 */
class GetHeadlineResponse : BaseResponse() {
    @SerializedName("articles")
    @Expose
     var articles: List<Articles>? = null
}