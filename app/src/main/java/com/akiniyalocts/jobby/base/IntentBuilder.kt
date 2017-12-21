package com.akiniyalocts.jobby.base

import android.content.Context
import android.content.Intent

/**
 * Created by anthonykiniyalocts on 9/14/17.
 */
interface IntentBuilder {
    fun buildIntent(context:Context) : Intent
}