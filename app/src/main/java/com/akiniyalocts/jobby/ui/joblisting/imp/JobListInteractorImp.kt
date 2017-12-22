package com.akiniyalocts.jobby.ui.joblisting.imp

import android.annotation.SuppressLint
import android.location.Location
import com.akiniyalocts.jobby.model.GithubApi
import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.joblisting.JobListInteractor
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
class JobListInteractorImp @Inject constructor(private val githubApi: GithubApi, private val fusedLocationProviderClient: FusedLocationProviderClient)
    : JobListInteractor {

    private var lastLocation : Location? = null

    interface JobListCallbacks{
        fun onJobsFetched(jobs : List<Job>)
        fun onFailureFetchingJobs(reason: String?)
    }

    @SuppressLint("MissingPermission")
    override fun fetchCurrentLocation(callback: JobListPresenterImp.Callback) {
        fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        lastLocation = it

                        this.fetchAllJobs(callback)
                    }
                }
    }

    override fun clearLastLocation(callback: JobListPresenterImp.Callback) {
        this.lastLocation = null
        this.fetchAllJobs(callback)
    }

    override fun fetchAllJobs(callback: JobListPresenterImp.Callback) {
        this.fetchJobs(null, null, callback)
    }

    override fun fetchJobs(cityName: String?, job: String?, callback: JobListPresenterImp.Callback) {
        githubApi.fetchPositions(this.lastLocation?.latitude, this.lastLocation?.longitude, cityName, job).enqueue(object : Callback<List<Job>> {
            override fun onFailure(call: Call<List<Job>>?, t: Throwable?) {
                callback.onFailureFetchingJobs(t?.localizedMessage)
            }

            override fun onResponse(call: Call<List<Job>>?, response: Response<List<Job>>?) {
                response?.let {
                    handleResponse(it, callback)
                }
            }
        })
    }

    private fun handleResponse(response: Response<List<Job>>, callback: JobListPresenterImp.Callback){
        if (response.isSuccessful) {
            response.body()?.let {
                callback.onJobsFetched(it)
            }
        } else if(response.errorBody() != null) {
            callback.onFailureFetchingJobs(response.errorBody()?.string())
        }
    }

}