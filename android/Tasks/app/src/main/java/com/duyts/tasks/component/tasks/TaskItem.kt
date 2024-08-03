package com.duyts.tasks.component.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duyts.tasks.model.Task


@Composable
fun TaskItem(
	task: Task,
	onTaskClick: (String?) -> Unit = {},
	onTaskCheckedChange: (Task, Boolean) -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxSize()
			.padding(8.dp, 8.dp, 8.dp, 8.dp)
			.clickable { onTaskClick(task.id) },
		elevation = CardDefaults.cardElevation(4.dp)
	) {
		Row(
			modifier = Modifier.padding(10.dp),
			verticalAlignment = Alignment.CenterVertically,
		) {
			Checkbox(
				checked = task.isCompleted,
				onCheckedChange = { onTaskCheckedChange(task, it) }
			)
			Text(
				text = task.title.orEmpty(),
				style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
				maxLines = 1,
				textDecoration = if (task.isCompleted) {
					TextDecoration.LineThrough
				} else {
					null
				}
			)
		}
	}
}