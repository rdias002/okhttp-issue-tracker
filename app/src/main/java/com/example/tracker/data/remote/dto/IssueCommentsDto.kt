package com.example.tracker.data.remote.dto

import android.os.Parcelable
import com.example.tracker.data.local.entity.IssueCommentEntity
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.model.Reactions
import com.example.tracker.domain.model.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class IssueCommentDto(

	@field:SerializedName("author_association")
	val authorAssociation: String? = null,

	@field:SerializedName("issue_url")
	val issueUrl: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Date? = null,

	@field:SerializedName("performed_via_github_app")
	val performedViaGithubApp: Boolean? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Date? = null,

	@field:SerializedName("reactions")
	val reactions: ReactionsDto? = null,

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("user")
	val user: UserDto? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null
) : Parcelable{
	fun toIssueCommentEntity() = IssueCommentEntity(
		id?:0,
		issueUrl?.split("/")?.lastOrNull()?.toIntOrNull()?:0,
		body?:"",
		createdAt?:Date(),
		updatedAt?:Date(),
		user?.toUser()?:User(),
		reactions?.toReactions()?:Reactions()
	)
}
