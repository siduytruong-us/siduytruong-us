package com.duyts.tasks.model


data class LocalUserAuthentication(
	val email: String,
	val passwordHash: String,
	val authToken: String,
	val displayName: String,
	val photoUrl: String,
)