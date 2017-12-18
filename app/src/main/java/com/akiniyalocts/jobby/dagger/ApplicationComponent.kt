package com.akiniyalocts.jobby.dagger

import com.akiniyalocts.jobby.dagger.application.ApiModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */

@Singleton
@Component(modules = [(ApiModule::class)])
interface ApplicationComponent {
    fun inject(application: JobbyApplication)
}