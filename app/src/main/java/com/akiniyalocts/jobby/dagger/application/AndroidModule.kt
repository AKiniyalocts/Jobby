package com.akiniyalocts.jobby.dagger.application

import android.content.Context
import com.akiniyalocts.jobby.R
import com.akiniyalocts.jobby.ui.DeviceInfo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * Provide any system related modules
 */
@Module
class AndroidModule(private val context: Context) {

    /**
     * FusedLocationProviderClient as a singleton
     */
    @Provides
    @Singleton
    fun locationProviderClient() : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun deviceInfo() = DeviceInfo(context.resources.getBoolean(R.bool.is_landscape))
}