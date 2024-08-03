package com.duyts.tasks.datasource.datastore.preference

import android.util.Log
import androidx.datastore.core.DataStore
import com.duyts.tasks.datastore.UserPreferences
import com.duyts.tasks.datastore.copy
import com.duyts.tasks.model.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferenceDataSource @Inject constructor(
	private val userPreferences: DataStore<UserPreferences>
) {
	val userData = userPreferences.data.map { userPref ->
		UserData(userPref.email)
	}

	suspend fun setEmail(email: String) {
		try {
			userPreferences.updateData {
				it.copy {
					this.email = email
				}
			}
		} catch (ioException: IOException) {
			Log.e("UserPreferences", "Failed to update user email preferences", ioException)
		}
	}


}