package com.duyts.tasks.repository

import com.duyts.tasks.datasource.datastore.preference.UserPreferenceDataSource
import com.duyts.tasks.feature.setting.DarkThemeConfig
import com.duyts.tasks.feature.setting.ThemeBrand
import com.duyts.tasks.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
	private val userPrefDataSource: UserPreferenceDataSource,
) : UserDataRepository {
	override val userData: Flow<UserData> = userPrefDataSource.userData

	override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
		userPrefDataSource.setDarkThemeConfig(darkThemeConfig)
	}

	override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
		userPrefDataSource.setThemeBrand(themeBrand)
	}

	override suspend fun setDynamicColor(useDynamicColor: Boolean) {
		userPrefDataSource.setDynamicColorPreference(useDynamicColor)
	}
}