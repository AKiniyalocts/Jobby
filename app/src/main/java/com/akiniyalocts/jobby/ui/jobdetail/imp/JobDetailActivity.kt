package com.akiniyalocts.jobby.ui.jobdetail.imp

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.akiniyalocts.jobby.R
import com.akiniyalocts.jobby.base.DaggerActivity
import com.akiniyalocts.jobby.base.IntentBuilder
import com.akiniyalocts.jobby.dagger.application.ApplicationComponent
import com.akiniyalocts.jobby.dagger.application.GlideApp
import com.akiniyalocts.jobby.dagger.jobdetail.JobDetailModule
import com.akiniyalocts.jobby.databinding.JobDetailBinding
import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailPresenter
import com.akiniyalocts.jobby.ui.jobdetail.JobDetailView
import javax.inject.Inject

class JobDetailActivity : DaggerActivity(), JobDetailView {

    companion object :IntentBuilder{

        private val KEY_JOB = "key::job"

        override fun buildIntent(context: Context) = Intent(context, JobDetailActivity::class.java)

        fun buildJobIntent(context: Context, job: Job) : Intent{
            val i = buildIntent(context)
            i.putExtra(KEY_JOB, job)
            return i
        }
    }

    override fun inject(appComponent: ApplicationComponent) {
        appComponent.plus(JobDetailModule(this)).inject(this)
    }

    private lateinit var viewModel : JobViewModel

    private lateinit var binding : JobDetailBinding

    @Inject lateinit var presenter : JobDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_detail)

        viewModel = ViewModelProviders.of(this).get(JobViewModel::class.java)

        val job = intent.getParcelableExtra<Job>(KEY_JOB)

        job?.let {
            viewModel.job = it
        }

        presenter.attach(job)
    }

    override fun init() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showJobImage(companyLogo: String?) {
        GlideApp.with(this)
                .load(companyLogo)
                .placeholder(R.drawable.ic_job_24dp)
                .centerInside()
                .into(binding.companyLogo)
    }

    override fun showJobTitle(title: String, location:String) {
        supportActionBar?.title = title
        binding.toolbar.subtitle = location
    }

    override fun bindJob(job: Job) {
        binding.job = job
    }
}
