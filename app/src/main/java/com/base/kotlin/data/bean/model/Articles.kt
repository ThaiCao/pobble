package com.base.kotlin.data.bean.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by thai.cao on 11/12/2019.
 */
class Articles() : Parcelable {
//    "source":{
//        "id":null,
//        "name":"Forbes.com"
//    },
//    "author":"Gordon Kelly",
//    "title":"Costco Black Friday 2019: The Best New Live Deals - Forbes",
//    "description":"Costco's Black Friday 2019 sales are now live and there are some red hot TV deals in particular...",
//    "url":"https://www.forbes.com/sites/gordonkelly/2019/11/11/costco-black-friday-2019-the-best-new-live-deals/",
//    "urlToImage":"https://thumbor.forbes.com/thumbor/600x315/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F5dc0ddf3ca425400079c7058%2F960x0.jpg",
//    "publishedAt":"2019-11-12T01:38:39Z",
//    "content":"Costcos Black Friday ad is official and it is full of impressive deals which place the store near the top of your Black Friday 2019 destinations. But Costco has also started its daily live deals and here is my pick of the best new deals you need to know about… [+12867 chars]"

    @SerializedName("source")
    @Expose
    var source: SourceArticles? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    constructor(parcel: Parcel) : this() {
        source = parcel.readParcelable(SourceArticles::class.java.classLoader)
        author = parcel.readString()
        title = parcel.readString()
        description = parcel.readString()
        url = parcel.readString()
        urlToImage = parcel.readString()
        publishedAt = parcel.readString()
        content = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(source, flags)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Articles> {
        override fun createFromParcel(parcel: Parcel): Articles {
            return Articles(parcel)
        }

        override fun newArray(size: Int): Array<Articles?> {
            return arrayOfNulls(size)
        }
    }

}