package com.duyts.tasks.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable

const val LOGIN_ROUTE = "LOGIN_ROUTE"
fun NavController.navigateToLoginScreen(
	options: NavOptionsBuilder.() -> Unit = {}
) = navigate(LOGIN_ROUTE) {
	options()
}

fun NavGraphBuilder.loginScreen(onAuthSuccess: () -> Unit) {
	composable(route = LOGIN_ROUTE) {
		LoginScreen(
			onAuthSuccess = onAuthSuccess
		)
	}
}