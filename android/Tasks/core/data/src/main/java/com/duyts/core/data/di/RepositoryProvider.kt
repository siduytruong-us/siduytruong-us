package com.duyts.core.data.di

import com.duyts.core.data.repository.TaskRepository
import com.duyts.core.data.repository.TaskRepositoryImpl
import com.duyts.core.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvider {
	@Provides
	fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)
}