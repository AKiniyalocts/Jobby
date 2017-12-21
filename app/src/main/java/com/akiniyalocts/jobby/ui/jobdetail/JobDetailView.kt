package com.akiniyalocts.jobby.ui.jobdetail

import com.akiniyalocts.jobby.base.View
import com.akiniyalocts.jobby.model.Job

/**
 * Created by anthonykiniyalocts on 12/21/17.
 */
interface JobDetailView : View {
    fun showJobImage(companyLogo: String?)
    fun showJobTitle(title: String, location:String)
    fun bindJob(job: Job)
}