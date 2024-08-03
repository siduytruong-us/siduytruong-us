package com.duyts.tasks.repository

import com.duyts.tasks.model.LocalUserAuthentication
import com.duyts.tasks.model.UserData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.Flow

interface AuthenticateRepository {
	suspend fun authenticate(email: String, password: String): Result<Boolean>
	fun getUserData(): Flow<LocalUserAuthentication>
	fun isAuthenticated(): Flow<Boolean>
	suspend fun logout()
	suspend fun loginWithGoogle(account: GoogleSignInAccount)
}