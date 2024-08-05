package com.duyts.tasks.repository.di

import com.duyts.core.common.BackgroundScope
import com.duyts.core.datastore.auth.UserAuthenticationDataSource
import com.duyts.core.datastore.preferences.UserPreferenceDataSource
import com.duyts.tasks.repository.AuthenticateRepository
import com.duyts.tasks.repository.AuthenticateRepositoryImpl
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
	fun provideAuthenticateRepository(
		userAuthDataSource: UserAuthenticationDataSource,
		@BackgroundScope backgroundScope: CoroutineScope
	): AuthenticateRepository = AuthenticateRepositoryImpl(userAuthDataSource, backgroundScope)

	@Provides
	fun provideUserDataRepository(
		userPrefDataSource: UserPreferenceDataSource
	): UserDataRepository = UserDataRepositoryImpl(userPrefDataSource)
}