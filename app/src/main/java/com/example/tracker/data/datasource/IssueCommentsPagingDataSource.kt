package com.example.tracker.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.repository.IssuesRepository

class IssueCommentsPagingDataSource(
    private val issueId: Int,
    private val issuesRepository: IssuesRepository,
) : PagingSource<Int, IssueComment>() {

    override fun getRefreshKey(state: PagingState<Int, IssueComment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IssueComment> {
        return try {
            val pageNumber = params.key ?: 1
            val response = issuesRepository.getIssueComments(issueId,pageNumber)
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

    companion object{
        const val PAGE_SIZE = 30
    }
}