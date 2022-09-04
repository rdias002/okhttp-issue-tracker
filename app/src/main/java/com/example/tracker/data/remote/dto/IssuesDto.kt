package com.example.tracker.data.remote.dto

import android.os.Parcelable
import com.example.tracker.data.local.entity.IssueEntity
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.model.IssueLabel
import com.example.tracker.domain.model.Reactions
import com.example.tracker.domain.model.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class IssueDto(

	@field:SerializedName("assignees")
	val assignees: List<String>? = null,

	@field:SerializedName("created_at")
	val createdAt: Date? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val description: String? = null,

	@field:SerializedName("labels_url")
	val labelsUrl: String? = null,

	@field:SerializedName("author_association")
	val authorAssociation: String? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Date? = null,

	@field:SerializedName("performed_via_github_app")
	val performedViaGithubApp: String? = null,

	@field:SerializedName("comments_url")
	val commentsUrl: String? = null,

	@field:SerializedName("active_lock_reason")
	val activeLockReason: String? = null,

	@field:SerializedName("repository_url")
	val repositoryUrl: String? = null,

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("locked")
	val locked: Boolean? = null,

	@field:SerializedName("timeline_url")
	val timelineUrl: String? = null,

	@field:SerializedName("state_reason")
	val stateReason: String? = null,

	@field:SerializedName("comments")
	val comments: Int? = null,

	@field:SerializedName("closed_at")
	val closedAt: Date? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("labels")
	val labels: List<LabelsItemDto>? = null,

	@field:SerializedName("milestone")
	val milestone: MilestoneDto? = null,

	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("reactions")
	val reactions: ReactionsDto? = null,

	@field:SerializedName("assignee")
	val assignee: String? = null,

	@field:SerializedName("user")
	val user: UserDto? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@field:SerializedName("draft")
	val draft: Boolean? = null,

	@field:SerializedName("pull_request")
	val pullRequest: PullRequestDto? = null
) : Parcelable{
	fun toIssueEntity() = IssueEntity(
		number?:0,
		title?:"",
		description?:"",
		user?.toUser()?:User(),
		assignee?:"",
		assignees?: emptyList(),
		createdAt?: Date(),
		updatedAt?:Date(),
		closedAt?:Date(),
		comments?:0,
		state?:"",
		stateReason?:"",
		locked?:false,
		labels?.map { it.toLabel() }?: emptyList(),
		reactions?.toReactions()?:Reactions()
	)
}

@Parcelize
data class PullRequestDto(

	@field:SerializedName("patch_url")
	val patchUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("merged_at")
	val mergedAt: Date? = null,

	@field:SerializedName("diff_url")
	val diffUrl: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable

@Parcelize
data class UserDto(

	@field:SerializedName("gists_url")
	val gistsUrl: String? = null,

	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("following_url")
	val followingUrl: String? = null,

	@field:SerializedName("starred_url")
	val starredUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("followers_url")
	val followersUrl: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = null,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean? = null,

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("gravatar_id")
	val gravatarId: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String? = null
) : Parcelable{
	fun toUser() = User(
		id = id?:0,
		login = login?:"",
		avatarUrl = avatarUrl?:""
	)
}

@Parcelize
data class LabelsItemDto(

	@field:SerializedName("default")
	val jsonMemberDefault: Boolean? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null
) : Parcelable{
	fun toLabel() = IssueLabel(
		id = id?:0,
		name = name?:"",
		color = color?:"",
		description = description?:""
	)
}

@Parcelize
data class MilestoneDto(

	@field:SerializedName("creator")
	val creator: CreatorDto? = null,

	@field:SerializedName("closed_at")
	val closedAt: Date? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Date? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("closed_issues")
	val closedIssues: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("due_on")
	val dueOn: String? = null,

	@field:SerializedName("labels_url")
	val labelsUrl: String? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("open_issues")
	val openIssues: Int? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null
) : Parcelable

@Parcelize
data class CreatorDto(

	@field:SerializedName("gists_url")
	val gistsUrl: String? = null,

	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("following_url")
	val followingUrl: String? = null,

	@field:SerializedName("starred_url")
	val starredUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("followers_url")
	val followersUrl: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = null,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("gravatar_id")
	val gravatarId: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String? = null
) : Parcelable

@Parcelize
data class ReactionsDto(

	@field:SerializedName("confused")
	val confused: Int? = null,

	@field:SerializedName("-1")
	val downVote: Int? = null,

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("+1")
	val upVote: Int? = null,

	@field:SerializedName("rocket")
	val rocket: Int? = null,

	@field:SerializedName("hooray")
	val hooray: Int? = null,

	@field:SerializedName("eyes")
	val eyes: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("laugh")
	val laugh: Int? = null,

	@field:SerializedName("heart")
	val heart: Int? = null
) : Parcelable{
	fun toReactions() = Reactions(
		totalCount = totalCount?:0,
		downVote = downVote?:0,
		upVote = upVote?:0,
		confused = confused?:0,
		rocket = rocket?:0,
		hooray = hooray?:0,
		eyes = eyes?:0,
		laugh = laugh?:0,
		heart = heart?:0
	)
}
