package com.akiniyalocts.jobby.ui.jobdetail.imp

import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailInteractor

/**
 * Created by anthonykiniyalocts on 12/21/17.
 */
class JobDetailInteractorImp : JobDetailInteractor {

    var currentJob: Job? = null

    override fun job(): Job? = currentJob

    override fun setJob(job: Job) {
        this.currentJob = job
    }

    override fun clearJob() {
        this.currentJob = null
    }
}