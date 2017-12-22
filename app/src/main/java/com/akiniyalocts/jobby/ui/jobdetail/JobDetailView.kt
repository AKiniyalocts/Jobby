package com.akiniyalocts.jobby.ui.jobdetail

import com.akiniyalocts.jobby.base.View
import com.akiniyalocts.jobby.model.Job

/**
 * Created by anthonykiniyalocts on 12/21/17.
 *
 * JobDetailView contract
 */
interface JobDetailView : View {
    fun showJobImage(companyLogo: String?)
    fun showJobTitle(job: Job)
    fun bindJob(job: Job)
    fun goToWebsite(companyUrl: String?)
}