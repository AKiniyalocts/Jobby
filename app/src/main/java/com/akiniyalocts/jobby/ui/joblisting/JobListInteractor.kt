package com.akiniyalocts.jobby.ui.joblisting

import com.akiniyalocts.jobby.ui.joblisting.imp.JobListPresenterImp

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
interface JobListInteractor {
    fun fetchJobs(cityName:String?, job:String?, callback: JobListPresenterImp.Callback)
    fun fetchCurrentLocation()
    fun clearLastLocation()
    fun fetchAllJobs(callback: JobListPresenterImp.Callback)
}