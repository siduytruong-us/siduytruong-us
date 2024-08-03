package com.duyts.tasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.duyts.tasks.ui.MainApp
import com.duyts.tasks.ui.theme.TasksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val appState = rememberAppState()
			TasksTheme {
				MainApp(appState)
			}
		}
	}
}