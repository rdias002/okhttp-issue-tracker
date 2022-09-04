package com.example.tracker.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reactions(
    val totalCount: Int = 0,
    val downVote: Int = 0,
    val upVote: Int = 0,
    val confused: Int = 0,
    val rocket: Int = 0,
    val hooray: Int = 0,
    val eyes: Int = 0,
    val laugh: Int = 0,
    val heart: Int = 0
) : Parcelable
