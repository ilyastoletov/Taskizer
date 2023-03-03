package com.appninjas.data.storage

import androidx.room.*
import com.appninjas.domain.model.Task

@Dao
interface TaskDao {

    @Insert(entity = TaskDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: TaskDbModel)

    @Query("SELECT * FROM tasks")
    suspend fun getTasksList(): List<TaskDbModel>

    @Update(entity = TaskDbModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateTask(task: TaskDbModel)

    @Delete(entity = TaskDbModel::class)
    suspend fun deleteTask(task: TaskDbModel)

}