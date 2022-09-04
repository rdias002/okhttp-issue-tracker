package com.example.tracker.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.databinding.ItemLayoutCommentBinding
import com.example.tracker.domain.model.IssueComment

class IssueCommentsPagingAdapter() :
    PagingDataAdapter<IssueComment, IssueCommentsPagingAdapter.IssuesViewHolder>(IssueComparator) {

    inner class IssuesViewHolder(private val binding: ItemLayoutCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IssueComment?) {
            item?.let {
                binding.login.text = item.user.login
                binding.body.text = item.body
            }
        }
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val binding =
            ItemLayoutCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssuesViewHolder(binding)
    }

    object IssueComparator : DiffUtil.ItemCallback<IssueComment>() {
        override fun areItemsTheSame(oldItem: IssueComment, newItem: IssueComment): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IssueComment, newItem: IssueComment): Boolean {
            return oldItem == newItem
        }
    }

}