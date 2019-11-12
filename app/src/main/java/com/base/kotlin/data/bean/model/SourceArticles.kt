package com.base.kotlin.data.bean.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by thai.cao on 11/12/2019.
 */
class SourceArticles() : Parcelable {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SourceArticles> {
        override fun createFromParcel(parcel: Parcel): SourceArticles {
            return SourceArticles(parcel)
        }

        override fun newArray(size: Int): Array<SourceArticles?> {
            return arrayOfNulls(size)
        }
    }


}