package com.duyts.tasks.core.di

import com.duyts.tasks.core.AppDispatcher
import com.duyts.tasks.core.ApplicationScope
import com.duyts.tasks.core.BackgroundScope
import com.duyts.tasks.core.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {
	@Provides
	@Singleton
	@ApplicationScope
	fun provideApplicationScope(
		@Dispatcher(AppDispatcher.Default) dispatcher: CoroutineDispatcher
	): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

	@Provides
	@Singleton
	@BackgroundScope
	fun provideBackgroundScope(
		@Dispatcher(AppDispatcher.IO) dispatcher: CoroutineDispatcher
	): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

}