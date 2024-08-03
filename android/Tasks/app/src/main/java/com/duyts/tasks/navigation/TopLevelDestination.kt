package com.duyts.tasks.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.duyts.tasks.icons.AppIcon

enum class TopLevelDestination(
	val selectedIcon: ImageVector,
	val unselectedIcon: ImageVector,
	val iconTextId: String,
	val titleTextId: String,
) {
	HOME(
		selectedIcon = AppIcon.RoundedList,
		unselectedIcon = AppIcon.OutlinedList,
		iconTextId = "Home",
		titleTextId = "Home",
	),
	PROFILE(
		selectedIcon = AppIcon.RoundedProfile,
		unselectedIcon = AppIcon.OutlinedProfile,
		iconTextId = "Profile",
		titleTextId = "Profile",
	)
}