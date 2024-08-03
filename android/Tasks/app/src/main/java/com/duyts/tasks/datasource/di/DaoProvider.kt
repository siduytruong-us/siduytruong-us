package com.duyts.tasks.datasource.di

import com.duyts.tasks.datasource.room.AppDatabase
import com.duyts.tasks.datasource.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoProvider {
	@Provides
	fun provideDao(database: AppDatabase): TaskDao = database.taskDao()

}