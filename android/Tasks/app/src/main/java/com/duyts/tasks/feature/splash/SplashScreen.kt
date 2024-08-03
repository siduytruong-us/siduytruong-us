package com.duyts.tasks.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
	viewModel: SplashScreenViewModel = hiltViewModel(),
	onAuthenticate: (Boolean) -> Unit
) {
	val uiState = viewModel.uiState.collectAsState().value
	LaunchedEffect(uiState) {
		if (uiState is SplashScreenUiState.Success) {
			onAuthenticate.invoke(uiState.isAuthenticated)
		}
	}

	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
	}
}