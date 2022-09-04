package com.example.tracker.presentation.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tracker.databinding.ItemLayoutIssueBinding
import com.example.tracker.domain.model.Issue
import java.text.SimpleDateFormat
import java.util.*

class IssuesPagingAdapter(private val onClick: (Issue) -> Unit) :
    PagingDataAdapter<Issue, IssuesPagingAdapter.IssuesViewHolder>(IssueComparator) {

    inner class IssuesViewHolder(private val binding: ItemLayoutIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Issue?) {
            item?.let {
                binding.title.text = it.title
                binding.description.text = it.description
                binding.user.text = it.user.login
                val sf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.date.text = "(Updated on ${sf.format(it.updatedAt)})"

                binding.state.text = it.state.uppercase()
                if(it.state.lowercase() == "closed"){
                    binding.state.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#8957E5"))
                }else if(it.state.lowercase() == "open"){
                    binding.state.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#238636"))
                }



                Glide.with(binding.avatar)
                    .load(it.user.avatarUrl)
                    .into(binding.avatar)

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