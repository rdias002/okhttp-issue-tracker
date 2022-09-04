package com.example.tracker.presentation.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tracker.databinding.FragmentIssueDetailsBinding
import com.example.tracker.presentation.IssuesViewModel
import com.example.tracker.presentation.adapters.IssueCommentsPagingAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.noties.markwon.Markwon
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class IssueDetailsFragment : Fragment() {

    private lateinit var binding: FragmentIssueDetailsBinding
    private val args: IssueDetailsFragmentArgs by navArgs()
    private val viewModel: IssuesViewModel by activityViewModels()
    private val adapter = IssueCommentsPagingAdapter()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentIssueDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            bottomSheetBehavior = BottomSheetBehavior.from(binding.standardBottomSheet)
            args.issue.let {
                val sf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val summaryTxt = "${it.user.login} opened this on ${sf.format(it.createdAt)}"
                val titleTxt = it.title + " #" + it.number
                val markwon = Markwon.create(requireContext())
                markwon.setMarkdown(binding.body, it.description)

                if(it.state.lowercase() == "closed"){
                    state.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#8957E5"))
                }else if(it.state.lowercase() == "open"){
                    state.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#238636"))
                }

                title.text = titleTxt
                summary.text = summaryTxt
                comments.text = "${it.comments} Comments"
                state.text = it.state.uppercase()

                Glide.with(binding.avatar)
                    .load(it.user.avatarUrl)
                    .into(binding.avatar)

                commentsList.adapter = adapter
                commentsList.setOnScrollChangeListener { _, _, _, _, _ ->
                    if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.isDraggable =
                            (commentsList.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0
                    } else {
                        bottomSheetBehavior.isDraggable = true
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if(args.issue.comments > 0){
                    viewModel.getIssueComments(args.issue.number).collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }
    }
}