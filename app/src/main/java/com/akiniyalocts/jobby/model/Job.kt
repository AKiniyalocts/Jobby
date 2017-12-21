package com.akiniyalocts.jobby.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
data class Job(
        var id : String,
        @SerializedName("created_at") var createdAt : String,
        var title : String,
        var location : String,
        var type : String,
        var description : String,
        @SerializedName("how_to_apply") var howToApply : String,
        var company : String,
        @SerializedName("company_url") var companyUrl : String?,
        @SerializedName("company_logo") var companyLogo : String?,
        @SerializedName("url")var jobUrl : String?
        ) : Parcelable{
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(id)
                parcel.writeString(createdAt)
                parcel.writeString(title)
                parcel.writeString(location)
                parcel.writeString(type)
                parcel.writeString(description)
                parcel.writeString(howToApply)
                parcel.writeString(company)
                parcel.writeString(companyUrl)
                parcel.writeString(companyLogo)
                parcel.writeString(jobUrl)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Job> {
                override fun createFromParcel(parcel: Parcel): Job {
                        return Job(parcel)
                }

                override fun newArray(size: Int): Array<Job?> {
                        return arrayOfNulls(size)
                }
        }


}