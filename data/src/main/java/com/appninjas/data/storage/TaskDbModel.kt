package com.appninjas.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskDbModel(
    @PrimaryKey
    val taskId: Int,
    val taskDescription: String,
    val taskStatus: Boolean
)