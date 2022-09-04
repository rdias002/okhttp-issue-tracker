package com.example.tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tracker.data.local.entity.IssueCommentEntity
import com.example.tracker.data.local.entity.IssueEntity

@Database(
    entities = [IssueEntity::class, IssueCommentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class IssuesDatabase : RoomDatabase() {

    abstract val issuesDao: IssuesDao

}