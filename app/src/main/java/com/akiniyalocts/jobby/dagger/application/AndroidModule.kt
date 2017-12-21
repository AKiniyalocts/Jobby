package com.akiniyalocts.jobby.dagger.application

import android.content.Context
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by anthonykiniyalocts on 12/19/17.
 */
@Module
class AndroidModule(private val context: Context) {
    @Provides
    @Singleton
    fun locationProviderClient() = LocationServices.getFusedLocationProviderClient(context)
}