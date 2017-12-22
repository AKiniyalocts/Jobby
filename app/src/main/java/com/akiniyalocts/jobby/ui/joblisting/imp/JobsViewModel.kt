package com.akiniyalocts.jobby.ui.joblisting.imp

import android.arch.lifecycle.ViewModel
import com.akiniyalocts.jobby.model.Job

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
class JobsViewModel : ViewModel() {
    var jobs : List<Job> = emptyList()
}