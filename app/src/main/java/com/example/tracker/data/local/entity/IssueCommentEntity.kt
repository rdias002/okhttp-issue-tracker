package com.example.tracker.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tracker.domain.model.IssueComment
import com.example.tracker.domain.model.Reactions
import com.example.tracker.domain.model.User
import java.util.*

@Entity(tableName = "comments_table")
data class IssueCommentEntity(
    @PrimaryKey val id: Long = 0,
    val issueNo: Int = 0,
    val body: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    @Embedded(prefix = "user_") val user: User = User(),
    @Embedded val reactions: Reactions = Reactions(),
) {
    fun toIssueComment() = IssueComment(
        id,
        issueNo,
        body,
        createdAt,
        updatedAt,
        reactions,
        user
    )
}
