package com.example.tracker.data.paging_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.repository.IssuesRepository

class IssuesPagingDataSource(
    private val issuesRepository: IssuesRepository,
) : PagingSource<Int, Issue>() {

    override fun getRefreshKey(state: PagingState<Int, Issue>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Issue> {
        return try {
            val pageNumber = params.key ?: 1
            val response = issuesRepository.getIssues(pageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}