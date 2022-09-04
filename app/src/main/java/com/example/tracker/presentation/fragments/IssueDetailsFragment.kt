package com.example.tracker.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.tracker.R
import com.example.tracker.databinding.FragmentIssueDetailsBinding
import com.example.tracker.presentation.IssuesViewModel
import com.example.tracker.presentation.adapters.IssueCommentsPagingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class IssueDetailsFragment : Fragment() {

    private lateinit var binding: FragmentIssueDetailsBinding
    private val args: IssueDetailsFragmentArgs by navArgs()
    private val viewModel : IssuesViewModel by activityViewModels()
    private val adapter = IssueCommentsPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssueDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            args.issue.let {
                val text = it.title + " #" + it.number
                title.text = text
                creator.text = it.user.login
                body.text = it.description
                commentsList.adapter = adapter
                commentsList.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
                commentsList.isNestedScrollingEnabled = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getIssueComments(args.issue.number).collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}