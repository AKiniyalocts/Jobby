package com.akiniyalocts.jobby.ui.joblisting.imp

import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.joblisting.JobListInteractor
import com.akiniyalocts.jobby.ui.joblisting.JobListPresenter
import com.akiniyalocts.jobby.ui.joblisting.JobListView

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
class JobListPresenterImp(val jobListView: JobListView, val jobListInteractor: JobListInteractor) : JobListPresenter {

    private val callback = Callback()

    override fun attach(freshState : Boolean) {
        jobListView.init()

        if(freshState) {
            jobListInteractor.fetchAllJobs(callback)
        }
    }

    override fun searchWithCityName(cityName: String, job: String) {
        jobListView.setRefreshing(true)
        jobListInteractor.fetchJobs(cityName, job, callback)
    }

    override fun enableCoarseLocation() {
        jobListInteractor.fetchCurrentLocation()
    }

    override fun disableCoarseLocation() {
        jobListInteractor.clearLastLocation()
    }

    override fun detach() {

    }

    inner class Callback : JobListInteractorImp.JobListCallbacks{

        override fun onJobsFetched(jobs: List<Job>) {
            jobListView.setRefreshing(false)
            jobListView.showJobs(jobs)
        }

        override fun onFailureFetchingJobs(reason: String?) {
            jobListView.setRefreshing(false)
            jobListView.showFailure(reason)
        }
    }
}