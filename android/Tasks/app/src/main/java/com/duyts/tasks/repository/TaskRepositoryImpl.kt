package com.duyts.tasks.repository

import com.duyts.tasks.datasource.room.TaskDao
import com.duyts.tasks.model.Task
import com.duyts.tasks.model.toEntity
import com.duyts.tasks.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
	private val taskDao: TaskDao
) : TaskRepository {
	override fun observeAll(): Flow<List<Task>> =
		taskDao.observeAll().distinctUntilChanged().map { entities ->
			entities.map { it.toModel() }
		}

	override suspend fun insert(task: Task) {
		taskDao.insert(task.toEntity())
	}

	override suspend fun update(task: Task) {
		taskDao.update(task.toEntity())
	}

	override suspend fun deleteById(id: String) {
		taskDao.deleteById(id)
	}

	override suspend fun getById(id: String) = taskDao.getById(id)?.toModel()
}