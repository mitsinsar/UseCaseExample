package com.mitsinsar.mvvmexample.utils.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail

class SimpleUserDiffUtil : DiffUtil.ItemCallback<SimpleUserDetail>() {
    override fun areItemsTheSame(oldItem: SimpleUserDetail, newItem: SimpleUserDetail): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: SimpleUserDetail, newItem: SimpleUserDetail): Boolean {
        return oldItem == newItem
    }
}
