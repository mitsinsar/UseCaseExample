package com.mitsinsar.mvvmexample.ui.userlist

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail
import com.mitsinsar.mvvmexample.utils.diffutil.SimpleUserDiffUtil

class UserListAdapter(
    private val onSimpleUserClick: (SimpleUserDetail) -> Unit
) : ListAdapter<SimpleUserDetail, SimpleUserViewHolder>(SimpleUserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleUserViewHolder {
        return SimpleUserViewHolder.create(parent).apply {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onSimpleUserClick(getItem(adapterPosition))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SimpleUserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
