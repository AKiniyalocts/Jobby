package com.akiniyalocts.jobby.ui.jobdetail.imp

import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailInteractor
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailPresenter
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailView

/**
 * Created by anthonykiniyalocts on 12/21/17.
 *
 * Display job information on our view
 */
class JobDetailPresenterImp(val jobDetailView: JobDetailView, val jobDetailInteractor:JobDetailInteractor) : JobDetailPresenter {

    override fun attach(job: Job) {
        jobDetailView.init()

        jobDetailInteractor.setJob(job)//store current job in our interactor

        //display job information
        jobDetailView.bindJob(job)
        jobDetailView.showJobImage(job.companyLogo)
        jobDetailView.showJobTitle(job)
    }

    override fun detach() {
        jobDetailInteractor.clearJob()
    }

    override fun showCompanyWebsite() {
        jobDetailInteractor.job()?.let {
            jobDetailView.goToWebsite(it.companyUrl)
        }
    }

    override fun showJobListing() {
        jobDetailInteractor.job()?.let {
            jobDetailView.goToWebsite(it.jobUrl)
        }
    }
}