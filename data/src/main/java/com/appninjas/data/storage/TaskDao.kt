package com.appninjas.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appninjas.domain.model.Task

@Dao
interface TaskDao {

    @Insert(entity = TaskDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: TaskDbModel)

    @Query("SELECT * FROM tasks")
    suspend fun getTasksList(): List<TaskDbModel>

}