package com.akiniyalocts.jobby.ui.jobdetail

import com.akiniyalocts.jobby.model.Job

/**
 * Created by anthonykiniyalocts on 12/21/17.
 *
 * JobDetailPresenter contract
 */
interface JobDetailPresenter {
    fun attach(job: Job)
    fun detach()
    fun showCompanyWebsite()
    fun showJobListing()
}