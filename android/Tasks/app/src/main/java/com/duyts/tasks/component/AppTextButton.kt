package com.duyts.tasks.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NiaTextButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	content: @Composable RowScope.() -> Unit,
) {
	TextButton(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		colors = ButtonDefaults.textButtonColors(
			contentColor = MaterialTheme.colorScheme.onBackground,
		),
		content = content,
	)
}
