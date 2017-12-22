package com.akiniyalocts.jobby.base

import android.content.Context
import android.content.Intent

/**
 * Intent contract for activities
 */
interface IntentBuilder {
    fun buildIntent(context:Context) : Intent
}