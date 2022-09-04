package com.example.tracker.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long = 0,
    val login: String = "",
    val avatarUrl: String = "",
) : Parcelable