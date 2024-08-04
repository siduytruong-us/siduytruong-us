package com.duyts.tasks.repository

import com.duyts.tasks.feature.setting.DarkThemeConfig
import com.duyts.tasks.feature.setting.ThemeBrand
import com.duyts.tasks.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
	val userData: Flow<UserData>

	suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
	suspend fun setThemeBrand(themeBrand: ThemeBrand)
	suspend fun setDynamicColor(useDynamicColor: Boolean)
}