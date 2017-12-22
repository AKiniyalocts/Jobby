package com.akiniyalocts.jobby.dagger.jobdetail

import com.akiniyalocts.jobby.base.ActivityScope
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailInteractor
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailPresenter
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailView
import com.akiniyalocts.jobby.ui.jobdetail.imp.JobDetailPresenterImp
import dagger.Module
import dagger.Provides

/**
 * Created by anthonykiniyalocts on 12/21/17.
 *
 * Provide any dependencies needed for JobDetailActivity
 */
@Module
class JobDetailModule(private val jobDetailView:JobDetailView) {

    @Provides
    @ActivityScope
    fun provideJobDetailView() = jobDetailView

    @Provides
    @ActivityScope
    fun provideJobDetailPresenter(jobDetailInteractor: JobDetailInteractor) : JobDetailPresenter = JobDetailPresenterImp(jobDetailView, jobDetailInteractor)

}