package com.akiniyalocts.jobby.ui.joblisting

import com.akiniyalocts.jobby.ui.joblisting.imp.JobListPresenterImp

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * JobListInteractor contract
 */
interface JobListInteractor {
    fun fetchJobs(cityName:String?, job:String?, callback: JobListPresenterImp.Callback)
    fun fetchCurrentLocation(callback: JobListPresenterImp.Callback)
    fun clearLastLocation(callback: JobListPresenterImp.Callback)
    fun fetchAllJobs(callback: JobListPresenterImp.Callback)
}