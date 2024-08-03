package com.duyts.tasks.model

data class UserData(
	val email: String,
) {
	val isAuthenticated: Boolean = email.isNotEmpty()
}

data class LocalUserAuthentication(
	val email: String,
	val passwordHash: String,
	val authToken: String,
	val displayName: String,
)