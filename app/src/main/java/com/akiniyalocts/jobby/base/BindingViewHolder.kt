package com.akiniyalocts.jobby.base

import android.support.v7.widget.RecyclerView

/**
 * Base ViewHolder class contract forcing a binding pattern
 */
abstract class BindingViewHolder<M>(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(viewModel: M)
}
