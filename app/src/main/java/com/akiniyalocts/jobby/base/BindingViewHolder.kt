package com.akiniyalocts.jobby.base

import android.support.v7.widget.RecyclerView

abstract class BindingViewHolder<M>(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(viewModel: M)
}
