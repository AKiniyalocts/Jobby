package com.akiniyalocts.github_api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
data class JobListing(
        var id : String,
        @SerializedName("created_at") var createdAt : String,
        var title : String,
        var location : String,
        var type : String,
        var description : String,
        @SerializedName("how_to_apply") var howToApply : String,
        var company : String,
        @SerializedName("company_url") var companyUrl : String,
        @SerializedName("company_logo") var companyLogo : String,
        var jobUrl : String
        )