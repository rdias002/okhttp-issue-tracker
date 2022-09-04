package com.example.tracker.domain.repository

import com.example.tracker.core.utils.Resource
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.model.IssueComment
import kotlinx.coroutines.flow.Flow

interface IssuesRepository{
    suspend fun getIssues(pageNo: Int = 1): List<Issue>
    suspend fun getIssueComments(issueId: Int, pageNo: Int): List<IssueComment>
}