package com.akiniyalocts.jobby.dagger.joblisting

import com.akiniyalocts.jobby.base.ActivityScope
import com.akiniyalocts.jobby.ui.joblisting.JobListInteractor
import com.akiniyalocts.jobby.ui.joblisting.JobListPresenter
import com.akiniyalocts.jobby.ui.joblisting.JobListView
import com.akiniyalocts.jobby.ui.joblisting.imp.JobListAdapter
import com.akiniyalocts.jobby.ui.joblisting.imp.JobListPresenterImp
import dagger.Module
import dagger.Provides

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * Provide any dependencies for JobListActivity
 */

@Module
class JobListModule(private val jobListView: JobListView) {

    @Provides
    @ActivityScope
    fun provideJobListView() = jobListView

    @Provides
    @ActivityScope
    fun provideJobListPresenter(jobListInteractor: JobListInteractor) : JobListPresenter = JobListPresenterImp(jobListView, jobListInteractor)

    @Provides
    @ActivityScope
    fun provideJobListListener() : JobListAdapter.JobListener = jobListView

}