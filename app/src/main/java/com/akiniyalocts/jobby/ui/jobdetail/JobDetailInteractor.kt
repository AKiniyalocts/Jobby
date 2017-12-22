package com.akiniyalocts.jobby.ui.jobdetail

import com.akiniyalocts.jobby.model.Job

/**
 * Created by anthonykiniyalocts on 12/21/17.
 *
 * JobDetailInteractor contract
 */
interface JobDetailInteractor {
    fun job() : Job?
    fun setJob(job:Job)
    fun clearJob()
}