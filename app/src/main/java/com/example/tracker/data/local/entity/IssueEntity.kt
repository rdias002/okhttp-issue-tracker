package com.example.tracker.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tracker.domain.model.Issue
import com.example.tracker.domain.model.IssueLabel
import com.example.tracker.domain.model.Reactions
import com.example.tracker.domain.model.User
import java.util.*

@Entity(tableName = "issues_table")
data class IssueEntity(
    @PrimaryKey val number: Int = 0,
    val title: String = "",
    val description: String = "",
    @Embedded(prefix = "user_") val user: User = User(),
    val assignee: String = "",
    val assignees: List<String> = emptyList(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val closedAt: Date = Date(),
    val comments: Int = 0,
    val state: String = "",
    val stateReason: String = "",
    val locked: Boolean = false,
    val labels: List<IssueLabel> = emptyList(),
    @Embedded val reactions: Reactions = Reactions(),
) {
    fun toIssue() = Issue(
        number,
        title,
        description,
        user,
        assignee,
        assignees,
        createdAt,
        updatedAt,
        closedAt,
        comments,
        state,
        stateReason,
        locked,
        labels,
        reactions
    )
}