package com.akiniyalocts.jobby.ui.joblisting

import android.widget.TextView
import com.akiniyalocts.jobby.base.View
import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.joblisting.imp.JobListAdapter

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
interface JobListView : View, JobListAdapter.JobListener,  TextView.OnEditorActionListener{
    fun requestLocationPermissions()
    fun showJobs(jobs: List<Job>)
    fun showFailure(reason: String?)
    fun setRefreshing(isRefreshing: Boolean)
}