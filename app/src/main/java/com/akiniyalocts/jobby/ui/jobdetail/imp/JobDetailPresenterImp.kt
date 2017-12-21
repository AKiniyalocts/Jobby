package com.akiniyalocts.jobby.ui.jobdetail.imp

import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailInteractor
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailPresenter
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailView

/**
 * Created by anthonykiniyalocts on 12/21/17.
 */
class JobDetailPresenterImp(val jobDetailView: JobDetailView, val jobDetailInteractor:JobDetailInteractor) : JobDetailPresenter {

    override fun attach(job: Job) {

        jobDetailView.init()
        jobDetailView.bindJob(job)
        jobDetailView.showJobImage(job.companyLogo)
        jobDetailView.showJobTitle(job.title, job.location)
    }
}