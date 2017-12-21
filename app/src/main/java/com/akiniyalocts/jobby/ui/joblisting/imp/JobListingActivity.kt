package com.akiniyalocts.jobby.ui.joblisting.imp

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.akiniyalocts.jobby.R
import com.akiniyalocts.jobby.base.DaggerActivity
import com.akiniyalocts.jobby.base.IntentBuilder
import com.akiniyalocts.jobby.dagger.application.ApplicationComponent
import com.akiniyalocts.jobby.dagger.joblisting.JobListModule
import com.akiniyalocts.jobby.databinding.JobListBinding
import com.akiniyalocts.jobby.model.Job
import com.akiniyalocts.jobby.ui.jobdetail.imp.JobDetailActivity
import com.akiniyalocts.jobby.ui.joblisting.JobListPresenter
import com.akiniyalocts.jobby.ui.joblisting.JobListView
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

class JobListingActivity : DaggerActivity(), JobListView {

    companion object : IntentBuilder{
        private const val REQUEST_ACCESS_COARSE_LOCATION = 1

        override fun buildIntent(context: Context): Intent = Intent(context, JobListingActivity::class.java)
    }

    override fun inject(appComponent: ApplicationComponent) {
        appComponent.plus(JobListModule(this)).inject(this)
    }


    @Inject lateinit var presenter : JobListPresenter

    @Inject lateinit var adapter : JobListAdapter

    lateinit var binding : JobListBinding

    private lateinit var viewModel : JobsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(JobsViewModel::class.java)

        presenter.attach(savedInstanceState == null)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun requestLocationPermissions() {
        checkLocationPermissions()
    }

    @AfterPermissionGranted(REQUEST_ACCESS_COARSE_LOCATION)
    fun checkLocationPermissions(){
        if(EasyPermissions.hasPermissions(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)){
            presenter.enableCoarseLocation()

        }else{
            EasyPermissions.requestPermissions(this, getString(R.string.location_permission_rationale), REQUEST_ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }


    override fun init() {
        setSupportActionBar(binding.toolbar)

        binding.refresh.isEnabled = false
        binding.refresh.setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        binding.refresh.setColorSchemeResources(R.color.colorAccent)

        binding.jobsList.layoutManager = LinearLayoutManager(this)
        binding.jobsList.adapter = adapter
        adapter.update(viewModel.getJobs())

        binding.cityName.setOnEditorActionListener(this)
        binding.jobDescription.setOnEditorActionListener(this)

        binding.myLocation.setOnCheckedChangeListener { compoundButton, checked ->
            if(checked){
                checkLocationPermissions()
            } else{
                presenter.disableCoarseLocation()
            }
        }

    }

    override fun onEditorAction(textView: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val cityName = binding.cityName.text.toString()
            val job = binding.jobDescription.text.toString()
            presenter.searchWithCityName(cityName, job)
            return true
        }
        return false
    }

    override fun setRefreshing(isRefreshing: Boolean) {
        binding.refresh.isRefreshing = isRefreshing
    }

    override fun onJobClicked(job: Job) {
        startActivity(JobDetailActivity.buildJobIntent(this, job))
    }

    override fun showJobs(jobs: List<Job>) {
        viewModel.setJobs(jobs)
        binding.jobs = jobs
        adapter.update(jobs)
    }

    override fun showFailure(reason: String?) {
        Snackbar.make(binding.root, "Failure: $reason", Snackbar.LENGTH_LONG).show()
    }
}
