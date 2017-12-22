package com.akiniyalocts.jobby.dagger.jobdetail

import com.akiniyalocts.jobby.base.ActivityScope
import com.akiniyalocts.jobby.ui.jobdetail.imp.JobDetailActivity
import dagger.Subcomponent

/**
 * Created by anthonykiniyalocts on 12/21/17.
 *
 * Subcomponent for our JobDetailActivity
 */
@ActivityScope
@Subcomponent(modules = [JobDetailModule::class])
interface JobDetailComponent {
    fun inject(jobDetailActivity: JobDetailActivity)
}