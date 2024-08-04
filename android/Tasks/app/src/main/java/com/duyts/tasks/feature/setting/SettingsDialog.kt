package com.duyts.tasks.feature.setting

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duyts.tasks.component.NiaTextButton
import com.duyts.tasks.ui.theme.supportsDynamicTheming

@Composable
fun SettingsDialog(
	onDismiss: () -> Unit,
	viewModel: SettingsViewModel = hiltViewModel()
) {
	val state by viewModel.settingsUiState.collectAsStateWithLifecycle()
	SettingsDialog(
		state = state, onDismiss = onDismiss,
		onChangeThemeBrand = viewModel::updateThemeBrand,
		onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
		onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
	)
}

@Composable
fun SettingsDialog(
	state: SettingUiState,
	onDismiss: () -> Unit,
	onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
	onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
	supportDynamicColor: Boolean = supportsDynamicTheming(),
) {
	val configuration = LocalConfiguration.current
	AlertDialog(
		properties = DialogProperties(usePlatformDefaultWidth = false),
		modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
		title = {
			Text(
				text = "Settings",
				style = MaterialTheme.typography.titleLarge,
			)
		},
		text = {
			HorizontalDivider()
			Column(Modifier.verticalScroll(rememberScrollState())) {
				when (state) {
					SettingUiState.Loading -> {
						Text(
							text = "Loading...",
							modifier = Modifier.padding(vertical = 16.dp),
						)
					}

					is SettingUiState.Success -> {
						SettingsPanel(
							settings = state.settings,
							supportDynamicColor = supportDynamicColor,
							onChangeThemeBrand = onChangeThemeBrand,
							onChangeDynamicColorPreference = onChangeDynamicColorPreference,
							onChangeDarkThemeConfig = onChangeDarkThemeConfig,
						)
					}
				}
				HorizontalDivider(Modifier.padding(top = 8.dp))
			}
		},
		onDismissRequest = { onDismiss() },
		confirmButton = { /*TODO*/ })
}


@Composable
private fun ColumnScope.SettingsPanel(
	settings: UserEditableSettings,
	supportDynamicColor: Boolean,
	onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
	onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
	SettingsDialogSectionTitle(text = "Theme")
	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = "Default",
			selected = settings.brand == ThemeBrand.DEFAULT,
			onClick = { onChangeThemeBrand(ThemeBrand.DEFAULT) },
		)
		SettingsDialogThemeChooserRow(
			text = "Android",
			selected = settings.brand == ThemeBrand.ANDROID,
			onClick = { onChangeThemeBrand(ThemeBrand.ANDROID) },
		)
	}
	AnimatedVisibility(visible = settings.brand == ThemeBrand.DEFAULT && supportDynamicColor) {
		Column {
			SettingsDialogSectionTitle(text = "Use dynamic color")
			Column(Modifier.selectableGroup()) {
				SettingsDialogThemeChooserRow(
					text = "Yes",
					selected = settings.useDynamicColor,
					onClick = { onChangeDynamicColorPreference(true) },
				)
				SettingsDialogThemeChooserRow(
					text = "No",
					selected = !settings.useDynamicColor,
					onClick = { onChangeDynamicColorPreference(false) },
				)
			}
		}
	}
	SettingsDialogSectionTitle(text = "Dark mode preference")
	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = "System default",
			selected = settings.darkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM) },
		)
		SettingsDialogThemeChooserRow(
			text = "Light",
			selected = settings.darkThemeConfig == DarkThemeConfig.LIGHT,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.LIGHT) },
		)
		SettingsDialogThemeChooserRow(
			text = "Dark",
			selected = settings.darkThemeConfig == DarkThemeConfig.DARK,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.DARK) },
		)
	}
}

@Composable
private fun SettingsDialogSectionTitle(text: String) {
	Text(
		text = text,
		style = MaterialTheme.typography.titleMedium,
		modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
	)
}

@Composable
fun SettingsDialogThemeChooserRow(
	text: String,
	selected: Boolean,
	onClick: () -> Unit,
) {
	Row(
		Modifier
			.fillMaxWidth()
			.selectable(
				selected = selected,
				role = Role.RadioButton,
				onClick = onClick,
			)
			.padding(12.dp),
		verticalAlignment = Alignment.CenterVertically,
	) {
		RadioButton(
			selected = selected,
			onClick = null,
		)
		Spacer(Modifier.width(8.dp))
		Text(text)
	}
}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
//	SettingsDialog()
}