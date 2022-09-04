package com.example.tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class IssueComment(
    val id: Long = 0,
    val issueNo: Int = 0,
    val body: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val reactions: Reactions = Reactions(),
    val user: User = User(),
) : Parcelable
