package com.akiniyalocts.jobby.ui.joblisting.imp

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akiniyalocts.jobby.R
import com.akiniyalocts.jobby.base.BindingViewHolder
import com.akiniyalocts.jobby.dagger.application.GlideApp
import com.akiniyalocts.jobby.databinding.JobItemBinding
import com.akiniyalocts.jobby.model.Job
import javax.inject.Inject

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 *
 */
class JobListAdapter @Inject constructor(private val listener:JobListener) : RecyclerView.Adapter<BindingViewHolder<Job>>() {

    interface JobListener{
        fun onJobClicked(job:Job)
    }

    private var jobs : List<Job> = emptyList()

    override fun onBindViewHolder(holder: BindingViewHolder<Job>?, position: Int) {
        holder?.bind(jobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingViewHolder<Job>  = JobViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.job_item, parent, false))

    override fun getItemCount() = jobs.size

    /**
     * Filter updates through JobDiff for free animations when updating
     */
    fun update(freshJobs : List<Job>){
        val result = DiffUtil.calculateDiff(JobDiff(freshJobs, jobs))
        this.jobs = freshJobs
        result.dispatchUpdatesTo(this)
    }

    /**
     * ViewHolder for our job listing
     *
     * R.layout.job_item
     */
    inner class JobViewHolder(itemView : View) : BindingViewHolder<Job>(itemView){

        private val binding : JobItemBinding = DataBindingUtil.bind(itemView)

        override fun bind(viewModel: Job) {
            binding.job = viewModel

            GlideApp.with(itemView)
                    .load(viewModel.companyLogo)
                    .placeholder(R.drawable.ic_job_24dp)
                    .optionalFitCenter()
                    .centerInside()
                    .into(binding.companyLogo)

            itemView.setOnClickListener {
                listener.onJobClicked(viewModel)
            }
        }
    }
}