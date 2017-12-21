package com.akiniyalocts.jobby.dagger.application

import com.akiniyalocts.jobby.dagger.JobbyApplication
import com.akiniyalocts.jobby.dagger.jobdetail.JobDetailComponent
import com.akiniyalocts.jobby.dagger.jobdetail.JobDetailModule
import com.akiniyalocts.jobby.dagger.joblisting.JobListComponent
import com.akiniyalocts.jobby.dagger.joblisting.JobListModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */

@Singleton
@Component(modules = [ApiModule::class, InteractorModule::class, AndroidModule::class])
interface ApplicationComponent {
    fun inject(application: JobbyApplication)

    fun plus(jobListModule: JobListModule) : JobListComponent

    fun plus(jobDetailModule: JobDetailModule) : JobDetailComponent
}