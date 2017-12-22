package com.akiniyalocts.jobby.ui.jobdetail.imp

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

/**
 * JobDetailActivity
 *
 * Display job and its full description.
 *
 * Build via: buildJobIntent(context: Context, job: Job)
 *
 */
class JobDetailActivity : DaggerActivity(), JobDetailView {

    companion object{
        private val KEY_JOB = "key::job"
        fun buildJobIntent(context: Context, job: Job) : Intent{
            val i = Intent(context, JobDetailActivity::class.java)
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_detail)// initialize binding

        viewModel = ViewModelProviders.of(this).get(JobViewModel::class.java) // initialize viewModel

        val job = intent.getParcelableExtra<Job>(KEY_JOB) // retrieve job via the launch intent

        job?.let {
            viewModel.job = it // if the job is not null, store it in our view model
        }

        presenter.attach(job)//start presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()//cleanup presenter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.job_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.view_listing -> presenter.showJobListing()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Setup method for our view.
     */
    override fun init() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.visitWebsite.setOnClickListener {
            presenter.showCompanyWebsite()
        }
    }

    /**
     * set our model object on our JobDetailBinding
     */
    override fun bindJob(job: Job) {
        binding.job = job
    }


    /**
     * Load company logo url if it is available, otherwise show placeholder
     */
    override fun showJobImage(companyLogo: String?) {
        GlideApp.with(this)
                .load(companyLogo)
                .placeholder(R.drawable.ic_job_24dp)
                .centerInside()
                .into(binding.companyLogo)
    }

    /**
     * Show job title in actionbar
     */
    override fun showJobTitle(job: Job) {
        supportActionBar?.title = job.title
    }

    /**
     * Launch company website if it is available
     */
    override fun goToWebsite(companyUrl: String?) {
        companyUrl?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(companyUrl)))
        }
    }
}
