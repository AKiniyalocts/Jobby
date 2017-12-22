package com.akiniyalocts.jobby.ui.joblisting.imp

import android.support.v7.util.DiffUtil
import com.akiniyalocts.jobby.model.Job

/**
 * Created by anthonykiniyalocts on 12/19/17.
 *
 * DiffUtil callback for Job model object.
 *
 */
class JobDiff(val fresh : List<Job>, val old : List<Job>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition].id == fresh[newItemPosition].id

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = fresh.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition] == fresh[newItemPosition]
}