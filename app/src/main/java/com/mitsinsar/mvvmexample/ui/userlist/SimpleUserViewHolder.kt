package com.mitsinsar.mvvmexample.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mitsinsar.mvvmexample.databinding.ItemSimpleUserBinding
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail

class SimpleUserViewHolder(
    private val binding: ItemSimpleUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(simpleUserDetail: SimpleUserDetail) {
        binding.userNameTextView.text = simpleUserDetail.fullName
    }

    companion object {
        fun create(parent: ViewGroup): SimpleUserViewHolder {
            val binding = ItemSimpleUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return SimpleUserViewHolder(binding)
        }
    }
}
