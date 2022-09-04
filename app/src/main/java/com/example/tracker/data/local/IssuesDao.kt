package com.example.tracker.data.local

import androidx.room.*
import com.example.tracker.data.paging_data_source.IssueCommentsPagingDataSource.Companion.PAGE_SIZE
import com.example.tracker.data.local.entity.IssueCommentEntity
import com.example.tracker.data.local.entity.IssueEntity

@Dao
interface IssuesDao {

    //Issues
    @Query("SELECT * FROM issues_table ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getIssues(offset: Int, limit: Int = PAGE_SIZE): List<IssueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssues(issues: List<IssueEntity>)

    //Comments
    @Query("SELECT * FROM comments_table WHERE issueNo = :issueNo ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getComments(issueNo: Int, offset: Int, limit: Int = PAGE_SIZE): List<IssueCommentEntity>

    @Query("SELECT COUNT(*) FROM comments_table WHERE issueNo = :issueNo")
    suspend fun getCommentsCount(issueNo: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(issues: List<IssueCommentEntity>)

}