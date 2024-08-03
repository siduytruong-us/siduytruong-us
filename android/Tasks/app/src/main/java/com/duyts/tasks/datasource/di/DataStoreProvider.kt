package com.duyts.tasks.datasource.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.duyts.tasks.core.AppDispatcher
import com.duyts.tasks.core.ApplicationScope
import com.duyts.tasks.core.Dispatcher
import com.duyts.tasks.datasource.datastore.preference.UserPreferencesSerializer
import com.duyts.tasks.datasource.datastore.authen.UserAuthenticationSerializer
import com.duyts.tasks.datastore.UserAuthentication
import com.duyts.tasks.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreProvider {
	@Provides
	@Singleton
	internal fun provideUserPreferencesDataStore(
		@ApplicationContext context: Context,
		@Dispatcher(AppDispatcher.IO) ioDispatcher: CoroutineDispatcher,
		@ApplicationScope scope: CoroutineScope,
		userPreferencesSerializer: UserPreferencesSerializer,
	): DataStore<UserPreferences> =
		DataStoreFactory.create(
			serializer = userPreferencesSerializer,
			scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
		) {
			context.dataStoreFile("user_preferences.pb")
		}

	@Provides
	@Singleton
	internal fun provideUserAuthenticationDataStore(
		@ApplicationContext context: Context,
		@Dispatcher(AppDispatcher.IO) ioDispatcher: CoroutineDispatcher,
		@ApplicationScope scope: CoroutineScope,
		userAuthenticationSerializer: UserAuthenticationSerializer,
	): DataStore<UserAuthentication> =
		DataStoreFactory.create(
			serializer = userAuthenticationSerializer,
			scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
		) {
			context.dataStoreFile("user_authentication.pb")
		}
}