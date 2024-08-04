package com.duyts.tasks.repository.di

import com.duyts.tasks.core.BackgroundScope
import com.duyts.tasks.datasource.datastore.authen.UserAuthenticationDataSource
import com.duyts.tasks.datasource.datastore.preference.UserPreferenceDataSource
import com.duyts.tasks.datasource.room.TaskDao
import com.duyts.tasks.repository.AuthenticateRepository
import com.duyts.tasks.repository.AuthenticateRepositoryImpl
import com.duyts.tasks.repository.TaskRepository
import com.duyts.tasks.repository.TaskRepositoryImpl
import com.duyts.tasks.repository.UserDataRepository
import com.duyts.tasks.repository.UserDataRepositoryImpl
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

	@Provides
	fun provideAuthenticateRepository(
		userAuthDataSource: UserAuthenticationDataSource,
		@BackgroundScope backgroundScope: CoroutineScope
	): AuthenticateRepository = AuthenticateRepositoryImpl(userAuthDataSource, backgroundScope)

	@Provides
	fun provideUserDataRepository(
		userPrefDataSource: UserPreferenceDataSource
	): UserDataRepository = UserDataRepositoryImpl(userPrefDataSource)
}