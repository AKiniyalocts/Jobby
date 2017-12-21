package com.akiniyalocts.jobby.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akiniyalocts.jobby.dagger.JobbyApplication
import com.akiniyalocts.jobby.dagger.application.ApplicationComponent


/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
abstract class DaggerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(JobbyApplication.appComponent)
    }

    protected abstract fun inject(appComponent: ApplicationComponent)

}