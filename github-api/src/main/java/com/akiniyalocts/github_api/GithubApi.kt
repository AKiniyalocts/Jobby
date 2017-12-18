package com.akiniyalocts.github_api

import com.akiniyalocts.github_api.model.JobListing
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
interface GithubApi {

    companion object {
        val BASE_URL = "https://jobs.github.com"
    }

    @GET("/positions.json")
    fun fetchPositions() : Call<List<JobListing>>

}