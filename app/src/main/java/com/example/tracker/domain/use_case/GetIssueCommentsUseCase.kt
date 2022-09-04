package com.example.tracker.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tracker.core.utils.Resource
import com.example.tracker.data.datasource.IssueCommentsPagingDataSource
import com.example.tracker.data.datasource.IssueCommentsPagingDataSource.Companion.PAGE_SIZE
import com.example.tracker.data.datasource.IssuesPagingDataSource
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.repository.IssuesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIssueCommentsUseCase @Inject constructor(
    private val repository: IssuesRepository
) {
    operator fun invoke(issueId: Int): Pager<Int, IssueComment> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            IssueCommentsPagingDataSource(issueId,repository)
        }
    }
}