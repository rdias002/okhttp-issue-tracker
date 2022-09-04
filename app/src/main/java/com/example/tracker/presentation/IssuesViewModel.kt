package com.example.tracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.use_case.GetIssueCommentsUseCase
import com.example.tracker.domain.use_case.GetIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    getIssuesUseCase: GetIssuesUseCase,
    private val getIssueCommentsUseCase: GetIssueCommentsUseCase
): ViewModel() {

    val issuesFlow = getIssuesUseCase().flow.cachedIn(viewModelScope)

    fun getIssueComments(issueId: Int): Flow<PagingData<IssueComment>> {
        return getIssueCommentsUseCase(issueId).flow.cachedIn(viewModelScope)
    }

}