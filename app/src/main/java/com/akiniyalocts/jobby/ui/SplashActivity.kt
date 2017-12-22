package com.akiniyalocts.jobby.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.akiniyalocts.jobby.R
import com.akiniyalocts.jobby.ui.joblisting.imp.JobListingActivity

/**
 * SplashActivity
 *
 * show app icon and application name
 */
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME : Long = 2 * 1000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()

        handler.postDelayed({

            startActivity(JobListingActivity.buildIntent(this))
            finish()

        }, SPLASH_TIME)
    }
}
