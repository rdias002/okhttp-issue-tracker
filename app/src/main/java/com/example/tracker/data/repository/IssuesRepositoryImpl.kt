package com.example.tracker.data.repository

import com.example.tracker.data.paging_data_source.IssueCommentsPagingDataSource.Companion.PAGE_SIZE
import com.example.tracker.data.local.IssuesDao
import com.example.tracker.data.remote.OkHttpApi
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.repository.IssuesRepository
import retrofit2.HttpException
import java.io.IOException

class IssuesRepositoryImpl(
    private val api: OkHttpApi,
    private val dao: IssuesDao,
) : IssuesRepository {

    override suspend fun getIssues(pageNo: Int): List<Issue>{
        try {
            val issues = api.getIssues(pageNo).map { it.toIssueEntity() }
            if (issues.isEmpty()) return emptyList()

            dao.insertIssues(issues)

        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return dao.getIssues(PAGE_SIZE*(pageNo-1)).map { it.toIssue() }
    }

    override suspend fun getIssueComments(
        issueId: Int,
        pageNo: Int
    ): List<IssueComment> {
        try {
            val comments = api.getComments(issueId, pageNo).map { it.toIssueCommentEntity() }
            if(comments.isEmpty()) return emptyList()

            dao.insertComments(comments)
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return dao.getComments(issueId,PAGE_SIZE*(pageNo-1)).map { it.toIssueComment() }
    }
}