package com.example.tracker.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tracker.data.paging_data_source.IssueCommentsPagingDataSource
import com.example.tracker.data.paging_data_source.IssueCommentsPagingDataSource.Companion.PAGE_SIZE
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.repository.IssuesRepository
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