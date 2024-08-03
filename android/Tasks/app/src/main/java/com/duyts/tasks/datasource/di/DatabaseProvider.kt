package com.duyts.tasks.datasource.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.duyts.tasks.datasource.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {
	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context) =
		Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
			.addMigrations(MIGRATION_1_2)
			.build()
}


val MIGRATION_1_2 = object : Migration(1, 2) {
	override fun migrate(db: SupportSQLiteDatabase) {
		db.execSQL("ALTER TABLE task ADD COLUMN is_completed INTEGER NOT NULL DEFAULT(0)")
	}
}