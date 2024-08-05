package com.duyts.tasks.repository

import com.duyts.core.common.BackgroundScope
import com.duyts.core.datastore.auth.UserAuthenticationDataSource
import com.duyts.core.datastore.model.UserAuthData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticateRepositoryImpl @Inject constructor(
	private val userAuthDataStore: UserAuthenticationDataSource,
	@BackgroundScope private val backgroundScope: CoroutineScope,
) : AuthenticateRepository {
	private val firebaseAuth = Firebase.auth
	override suspend fun authenticate(email: String, password: String): Result<Boolean> =
		backgroundScope.async {
			try {
				firebaseAuth.signInWithEmailAndPassword(email, password)
					.await().user?.let { authUser ->
						userAuthDataStore.setEmail(authUser.email)
						userAuthDataStore.setDisplayName(authUser.displayName)
						userAuthDataStore.setPhotoUrl(authUser.photoUrl?.encodedPath)
						userAuthDataStore.setAccessToken(
							firebaseAuth.getAccessToken(true).await().token
						)
						Result.success(true)
					} ?: Result.failure(Exception("No user login data!"))
			} catch (e: Exception) {
				Result.failure(e)
			}
		}.await()

	override fun getUserData(): Flow<UserAuthData> = userAuthDataStore.data
	override fun isAuthenticated(): Flow<Boolean> = userAuthDataStore.data.map {
		it.email.isNotEmpty()
	}

	override suspend fun logout() {
		userAuthDataStore.clear()
		firebaseAuth.signOut()
	}

	override suspend fun loginWithGoogle(account: GoogleSignInAccount) = account.run {
		userAuthDataStore.setEmail(email)
		userAuthDataStore.setDisplayName(displayName)
		userAuthDataStore.setPhotoUrl(photoUrl?.toString())
	}
}