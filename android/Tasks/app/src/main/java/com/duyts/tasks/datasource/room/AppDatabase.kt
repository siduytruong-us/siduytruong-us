package com.duyts.tasks.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duyts.tasks.datasource.entity.TaskEntity

@Database(
	entities = [TaskEntity::class], version = 2
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun taskDao(): TaskDao
}
