package com.akiniyalocts.jobby.base

/**
 * Created by anthonykiniyalocts on 8/2/17.
 */

interface HasComponent<T> {
    fun component(): T

    fun inject()
}
