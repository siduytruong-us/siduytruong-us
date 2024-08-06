package com.duyts.tasks.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duyts.core.common.network.AppDispatchers
import com.duyts.core.common.network.Dispatcher
import com.duyts.tasks.repository.AuthenticateRepositoryImpl
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val authenticateRepo: AuthenticateRepositoryImpl,
	@Dispatcher(AppDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
	private val _loginUiState = MutableStateFlow(LoginUiState())
	val loginUiState = _loginUiState

	fun onEmailChange(email: String) {
		_loginUiState.update { it.copy(email = email) }
	}

	fun onPasswordChange(password: String) {
		_loginUiState.update { it.copy(password = password) }
	}

	fun login() = viewModelScope.launch(context = dispatcher) {
		_loginUiState.apply {
			update { it.copy(isLoading = true) }
			val result = authenticateRepo.authenticate(value.email, value.password)
			if (result.getOrDefault(false)) {
				update { it.copy(isAuthenticated = true) }
			} else {
				update {
					it.copy(
						isAuthenticated = false,
						errorMessage = result.exceptionOrNull()?.message
					)
				}
			}
			update { it.copy(isLoading = false) }
		}
	}


	fun loginWithGoogle(task: Task<GoogleSignInAccount>?) = viewModelScope.launch {
		_loginUiState.apply {
			update { it.copy(isLoading = true) }
			task?.getResult(ApiException::class.java)?.let { account ->
				authenticateRepo.loginWithGoogle(account)
			}
			update { it.copy(isLoading = false) }
		}
	}

}

data class LoginUiState(
	val isLoading: Boolean = false,
	val isAuthenticated: Boolean = false,
	val email: String = "duytruong0702.us@gmail.com",
	val password: String = "vippergod12",
	val errorMessage: String? = null
)