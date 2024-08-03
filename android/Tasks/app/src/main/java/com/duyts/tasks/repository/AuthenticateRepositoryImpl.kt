package com.duyts.tasks.repository

import com.duyts.tasks.core.BackgroundScope
import com.duyts.tasks.datasource.datastore.authen.UserAuthenticationDataSource
import com.duyts.tasks.model.LocalUserAuthentication
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.Exception

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

	override fun getUserData(): Flow<LocalUserAuthentication> = userAuthDataStore.data
	override fun isAuthenticated(): Flow<Boolean> = userAuthDataStore.data.map {
		it.email.isNotEmpty()
	}

	override suspend fun logout() {
		userAuthDataStore.clear()
		firebaseAuth.signOut()
	}
}