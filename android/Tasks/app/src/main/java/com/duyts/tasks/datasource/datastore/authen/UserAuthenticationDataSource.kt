package com.duyts.tasks.datasource.datastore.authen

import android.util.Log
import androidx.datastore.core.DataStore
import com.duyts.tasks.datastore.UserAuthentication
import com.duyts.tasks.datastore.copy
import com.duyts.tasks.model.LocalUserAuthentication
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserAuthenticationDataSource @Inject constructor(
	private val userAuthentication: DataStore<UserAuthentication>
) {
	val data = userAuthentication.data.map { userAuth ->
		LocalUserAuthentication(
			email = userAuth.email,
			passwordHash = userAuth.passwordHash,
			displayName = userAuth.displayName,
			authToken = userAuth.authToken,
			photoUrl = userAuth.photoUrl
		)
	}

	suspend fun setEmail(email: String?) {
		try {
			userAuthentication.updateData {
				it.copy {
					this.email = email.orEmpty()
				}
			}
		} catch (ioException: IOException) {
			Log.e("UserPreferences", "Failed to update user email preferences", ioException)
		}
	}

	suspend fun setDisplayName(displayName: String?) {
		try {
			userAuthentication.updateData {
				it.copy {
					this.displayName = displayName.orEmpty()
				}
			}
		} catch (ioException: IOException) {
			Log.e("UserPreferences", "Failed to update user email preferences", ioException)
		}
	}

	suspend fun setPhotoUrl(photoUrl: String?) {
		try {
			userAuthentication.updateData {
				it.copy {
					this.photoUrl = photoUrl.orEmpty()
				}
			}
		} catch (ioException: IOException) {
			Log.e("UserPreferences", "Failed to update user email preferences", ioException)
		}
	}

	suspend fun setAccessToken(token: String?) {
		try {
			userAuthentication.updateData {
				it.copy {
					this.authToken = token.orEmpty()
				}
			}
		} catch (ioException: IOException) {
			Log.e("UserPreferences", "Failed to update user email preferences", ioException)
		}
	}

	suspend fun clear() {
		userAuthentication.updateData { it.toBuilder().clear().build() }
	}
}