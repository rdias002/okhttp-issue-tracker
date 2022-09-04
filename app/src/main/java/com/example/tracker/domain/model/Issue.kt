package com.example.tracker.domain.model

import android.os.Parcelable
import com.example.tracker.data.remote.dto.*
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Issue (
    val number: Int = 0,
    val title: String = "",
    val description: String = "",
    val user: User = User(),
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
    val reactions: Reactions = Reactions()
) : Parcelable