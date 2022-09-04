package com.example.tracker.data.remote

import com.example.tracker.data.remote.dto.IssueCommentDto
import com.example.tracker.data.remote.dto.IssueDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OkHttpApi {

    @GET("/repos/square/okhttp/issues")
    suspend fun getIssues(
        @Query("page") pageNo: Int
    ): List<IssueDto>

    @GET("repos/square/okhttp/issues/{issueId}/comments")
    suspend fun getComments(
        @Path("issueId") issueId: Int,
        @Query("page") pageNo: Int = 1
    ): List<IssueCommentDto>


    companion object{
        const val BASE_URL = "https://api.github.com/"
    }
}