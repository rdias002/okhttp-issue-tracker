package com.example.tracker.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.databinding.ItemLayoutIssueBinding
import com.example.tracker.domain.model.Issue

class IssuesPagingAdapter(private val onClick: (Issue) -> Unit) :
    PagingDataAdapter<Issue, IssuesPagingAdapter.IssuesViewHolder>(IssueComparator) {

    inner class IssuesViewHolder(private val binding: ItemLayoutIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Issue?) {
            item?.let {
                binding.title.text = it.title
                binding.description.text = it.description
                binding.root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {
        val binding =
            ItemLayoutIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssuesViewHolder(binding)
    }

    object IssueComparator : DiffUtil.ItemCallback<Issue>() {
        override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            // Id is unique.
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem == newItem
        }
    }

}