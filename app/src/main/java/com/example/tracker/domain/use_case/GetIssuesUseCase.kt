package com.example.tracker.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tracker.data.paging_data_source.IssuesPagingDataSource
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.repository.IssuesRepository
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: IssuesRepository,
) {

    operator fun invoke(): Pager<Int, Issue> {
        return Pager(PagingConfig(pageSize = 30)) {
            IssuesPagingDataSource(repository)
        }
    }
}