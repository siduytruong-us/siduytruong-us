package com.duyts.tasks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.duyts.tasks.AppState
import com.duyts.tasks.feature.login.loginScreen
import com.duyts.tasks.feature.login.navigateToLoginScreen
import com.duyts.tasks.feature.main.homeScreen
import com.duyts.tasks.feature.profile.profileScreen
import com.duyts.tasks.feature.splash.SPLASH_ROUTE
import com.duyts.tasks.feature.splash.splashScreen
import com.duyts.tasks.feature.taskdetail.navigateTaskDetailNavigation
import com.duyts.tasks.feature.taskdetail.taskDetail

@Composable
fun AppNavHost(
	modifier: Modifier = Modifier,
	appState: AppState,
	startDestination: String = SPLASH_ROUTE,
) {
	val navController = appState.navController
	NavHost(navController = navController, startDestination = startDestination) {
		splashScreen(
			onAuthenticate = { isAuthenticated ->
				if (isAuthenticated) {
					appState.navigateToTopLevelDestination(TopLevelDestination.HOME)
				} else {
					navController.navigateToLoginScreen()
				}
			}
		)

		homeScreen(
			onAddTask = navController::navigateTaskDetailNavigation,
			onTaskClick = navController::navigateTaskDetailNavigation
		)

		taskDetail(
			onBack = navController::popBackStack
		)

		profileScreen(
			onLogout = navController::navigateToLoginScreen,
		)

		loginScreen(
			onAuthSuccess = { appState.navigateToTopLevelDestination(TopLevelDestination.HOME) }
		)
	}
}