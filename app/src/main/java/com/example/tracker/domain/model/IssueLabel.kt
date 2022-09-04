package com.example.tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IssueLabel(
    val id: Long = 0,
    val name: String = "",
    val color: String = "",
    val description: String = "",
) : Parcelable
