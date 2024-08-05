package com.duyts.tasks.repository

import com.duyts.core.datastore.model.UserAuthData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.Flow

interface AuthenticateRepository {
	suspend fun authenticate(email: String, password: String): Result<Boolean>
	fun getUserData(): Flow<UserAuthData>
	fun isAuthenticated(): Flow<Boolean>
	suspend fun logout()
	suspend fun loginWithGoogle(account: GoogleSignInAccount)
}