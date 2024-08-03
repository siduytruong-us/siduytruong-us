package com.duyts.tasks.core.di

import com.duyts.tasks.core.AppDispatcher
import com.duyts.tasks.core.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
	@Provides
	@Dispatcher(AppDispatcher.IO)
	fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

	@Provides
	@Dispatcher(AppDispatcher.Default)
	fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}