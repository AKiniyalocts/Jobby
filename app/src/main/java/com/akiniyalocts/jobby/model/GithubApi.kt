package com.akiniyalocts.jobby.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
interface GithubApi {

    companion object {
        val BASE_URL = "https://jobs.github.com"
    }

    @GET("/positions.json")
    fun fetchPositions(@Query("lat") latitude: Double?, @Query("long") longitude: Double?, @Query("location") cityName: String?, @Query("description") job: String?) : Call<List<Job>>
}