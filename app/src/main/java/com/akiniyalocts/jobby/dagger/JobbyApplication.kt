package com.akiniyalocts.jobby.dagger

import android.app.Application
import com.akiniyalocts.jobby.dagger.application.*


/**
 * Created by anthonykiniyalocts on 12/18/17.
 *
 * Custom Application class used to setup dagger
 */
class JobbyApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: ApplicationComponent
    }


    override fun onCreate() {
        super.onCreate()

        createAppComponent()
    }

    private fun createAppComponent() {
        appComponent = DaggerApplicationComponent.builder()
                .apiModule(ApiModule(this))
                .interactorModule(InteractorModule())
                .androidModule(AndroidModule(this))
                .build()

        appComponent.inject(this)
    }
}