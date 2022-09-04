package com.example.tracker.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.FragmentIssuesListBinding
import com.example.tracker.presentation.IssuesViewModel
import com.example.tracker.presentation.adapters.IssuesPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class IssuesListFragment : Fragment() {

    private lateinit var binding: FragmentIssuesListBinding
    private val viewModel: IssuesViewModel by activityViewModels()
    private val adapter: IssuesPagingAdapter = IssuesPagingAdapter{
        val args = bundleOf("issue" to it)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssuesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.issuesFlow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        binding.issuesList.adapter = adapter
    }
}