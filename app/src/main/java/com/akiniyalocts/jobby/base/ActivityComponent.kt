package com.akiniyalocts.jobby.base

import com.akiniyalocts.jobby.dagger.application.ApplicationComponent

/**
 * Created by anthonykiniyalocts on 12/18/17.
 */
interface ActivityComponent<in T : ApplicationComponent> {

    fun inject(activity : T)
}