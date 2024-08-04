package com.duyts.tasks.model

import com.duyts.tasks.feature.setting.DarkThemeConfig
import com.duyts.tasks.feature.setting.ThemeBrand

data class UserData(
	val themeBrand: ThemeBrand,
	val darkThemeConfig: DarkThemeConfig,
	val useDynamicColor: Boolean,
)

data class LocalUserAuthentication(
	val email: String,
	val passwordHash: String,
	val authToken: String,
	val displayName: String,
	val photoUrl: String,
)