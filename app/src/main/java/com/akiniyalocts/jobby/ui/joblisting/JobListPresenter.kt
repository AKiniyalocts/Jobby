package com.akiniyalocts.jobby.ui.joblisting

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * JobListPresenter contract
 */
interface JobListPresenter {
    fun attach(freshState : Boolean)
    fun detach()
    fun searchWithCityName(cityName: String, job: String)
    fun enableCoarseLocation()
    fun disableCoarseLocation()
}