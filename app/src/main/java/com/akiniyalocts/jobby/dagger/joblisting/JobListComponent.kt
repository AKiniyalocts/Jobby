package com.akiniyalocts.jobby.dagger.joblisting

import com.akiniyalocts.jobby.base.ActivityScope
import com.akiniyalocts.jobby.ui.joblisting.imp.JobListingActivity
import dagger.Subcomponent

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * Subcomponent for our JobListActivity
 */
@ActivityScope
@Subcomponent(modules = [JobListModule::class])
interface JobListComponent {
    fun inject(jobListingActivity: JobListingActivity)
}