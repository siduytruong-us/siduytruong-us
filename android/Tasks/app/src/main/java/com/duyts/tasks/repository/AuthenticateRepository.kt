package com.duyts.tasks.repository

import com.duyts.tasks.model.LocalUserAuthentication
import com.duyts.tasks.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthenticateRepository {
	suspend fun authenticate(email: String, password: String): Result<Boolean>
	fun getUserData(): Flow<LocalUserAuthentication>
	fun isAuthenticated(): Flow<Boolean>
	suspend fun logout()
}