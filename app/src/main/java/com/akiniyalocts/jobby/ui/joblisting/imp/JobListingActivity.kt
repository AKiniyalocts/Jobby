package com.akiniyalocts.jobby.ui.joblisting.imp

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
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
import com.akiniyalocts.jobby.ui.DeviceInfo
import com.akiniyalocts.jobby.ui.jobdetail.imp.JobDetailActivity
import com.akiniyalocts.jobby.ui.joblisting.JobListPresenter
import com.akiniyalocts.jobby.ui.joblisting.JobListView
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

/**
 * JobListingActivity
 *
 * List any jobs available based on query parameters and location.
 *
 *
 */
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

    @Inject lateinit var deviceInfo : DeviceInfo

    lateinit var binding : JobListBinding

    private lateinit var viewModel : JobsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)//initialize JobListBinding
        viewModel = ViewModelProviders.of(this).get(JobsViewModel::class.java)//initialize viewModel

        presenter.attach(savedInstanceState == null)//start presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()//cleanup presenter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            binding.myLocation.isChecked = false//if the permission was not granted we need to uncheck our switch
        }
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun requestLocationPermissions() {
        checkLocationPermissions()
    }

    /**
     * Request location permissions
     */
    @AfterPermissionGranted(REQUEST_ACCESS_COARSE_LOCATION)
    fun checkLocationPermissions(){
        if(EasyPermissions.hasPermissions(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)){
            presenter.enableCoarseLocation()
        }else{
            EasyPermissions.requestPermissions(this, getString(R.string.location_permission_rationale), REQUEST_ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }


    /**
     * Initialize our view
     */
    override fun init() {
        setSupportActionBar(binding.toolbar)

        binding.refresh.isEnabled = false
        binding.refresh.setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        binding.refresh.setColorSchemeResources(R.color.colorAccent)

        if(deviceInfo.isLandscape){
            binding.jobsList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }else {
            binding.jobsList.layoutManager = LinearLayoutManager(this)
        }
        binding.jobsList.adapter = adapter
        adapter.update(viewModel.jobs)

        binding.cityName.setOnEditorActionListener(this)
        binding.jobDescription.setOnEditorActionListener(this)

        binding.myLocation.setOnCheckedChangeListener { _, checked ->
            if(checked){
                checkLocationPermissions()
            } else{
                presenter.disableCoarseLocation()
            }
        }

    }

    /**
     * EditText listener for query submission via the return button on the soft keyboard
     */
    override fun onEditorAction(textView: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val cityName = binding.cityName.text.toString()
            val job = binding.jobDescription.text.toString()
            presenter.searchWithCityName(cityName, job)
            return true
        }
        return false
    }

    /**
     * Show/Hide swipe refresh view
     */
    override fun setRefreshing(isRefreshing: Boolean) {
        binding.refresh.isRefreshing = isRefreshing
    }

    /**
     * go to JobDetailActivity
     */
    override fun onJobClicked(job: Job) {
        startActivity(JobDetailActivity.buildJobIntent(this, job))
    }

    /**
     * Update adapter with newly fetched jobs
     */
    override fun showJobs(jobs: List<Job>) {
        viewModel.jobs = jobs
        binding.jobs = jobs
        adapter.update(jobs)
    }

    /**
     * Display failure when fetching jobs
     */
    override fun showFailure(reason: String?) {
        Snackbar.make(binding.root, "Failure: $reason", Snackbar.LENGTH_LONG).show()
    }
}
