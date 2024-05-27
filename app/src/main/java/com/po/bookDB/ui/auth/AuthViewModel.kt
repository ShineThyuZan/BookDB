package com.po.bookDB.ui.auth

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.po.bookDB.ui.common.DataResult
import com.po.bookDB.ui.usecase.RegisterUseCase
import com.po.bookDB.ui.viewModel.LoginEvent
import com.po.bookDB.ui.viewModel.SignUpEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase : LoginUseCase,
) : ViewModel() {
    val currentUser = repository.currentUser
   val hasUser: Boolean
       get() = repository.hasUser()

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> get() = _loginEvent

    private val _signUpEvent = MutableSharedFlow<SignUpEvent>()
    val signUpEvent: SharedFlow<SignUpEvent> get() = _signUpEvent


    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(
            userName = userName
        )
    }

    fun onUserNameClear() {
        loginUiState = loginUiState.copy(
            userName = ""
        )
    }

    fun onPasswordNameChange(password: String) {
        loginUiState = loginUiState.copy(
            password = password
        )
    }

    fun onUserNameSignUpChange(userName: String) {
        loginUiState = loginUiState.copy(
            userNameSignUp = userName
        )
    }

    fun onPasswordSignUpChange(password: String) {
        loginUiState = loginUiState.copy(
            passwordSignUp = password
        )
    }

    fun onConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(
            confirmPasswordSignUp = password
        )
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() && loginUiState.password.isNotBlank()

    fun validateSignUpForm() =
        loginUiState.userNameSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()

    private fun validatePwNotMatch(): Boolean {
        return loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp
    }

   private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(loginUiState.userNameSignUp).matches()
    }

    fun createUser() = viewModelScope.launch {
        if (isEmailValid()) {
            if (!validatePwNotMatch()) {
                loginUiState = loginUiState.copy(
                    isLoading = true
                )
                delay(2000L)
                val result = registerUseCase(
                    userName = loginUiState.userNameSignUp,
                    password = loginUiState.passwordSignUp
                )
                loginUiState = when(result){
                    is DataResult.Error -> {
                        loginUiState.copy(
                            signUpError = true
                        )
                    }
                    is DataResult.Success -> {
                        _signUpEvent.emit(SignUpEvent.SignUpSuccess)
                        loginUiState.copy(isLoading = false)
                    }
                }
            } else {
                loginUiState = loginUiState.copy(
                    signUpError = true
                )
            }
        } else {
            loginUiState = loginUiState.copy(
                errorSignUpInvalidEmail = true
            )
        }
    }

    fun loginUser() = viewModelScope.launch {
        /* try {*/
        /*      if (!validateLoginForm()) {
                  throw IllegalArgumentException("email and password cannot empty")
              }
              loginUiState = loginUiState.copy(
                  isLoading = true
              )
              loginUiState = loginUiState.copy(
                  loginError = true
              )*/
        if (validateLoginForm()) {
            val result = loginUseCase(
                userName = loginUiState.userName,
                password = loginUiState.password
            )
            loginUiState = when(result){
                is DataResult.Error -> {
                    loginUiState.copy(
                        loginError = true
                    )
                }

                is DataResult.Success -> {
                    _loginEvent.emit(LoginEvent.LoginSuccess)
                    loginUiState.copy(isLoading = false)
                }
            }
        /*   repository.login(
                loginUiState.userName,
                loginUiState.password
            ) { isSuccessful ->
                loginUiState = if (isSuccessful) {
                    Toast.makeText(context, "success Login", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(
                        isSuccessLogin = true,
                        loginError = false,
                    )
                } else {
                    Toast.makeText(context, "fail Login", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(
                        isSuccessLogin = false,
                        loginError = false,
                    )
                }
            }*/
        } else {
            loginUiState = loginUiState.copy(
                loginError = true
            )
        }
        /*    } catch (e: Exception) {
                loginUiState = loginUiState.copy(
                    loginError = true
                )
                e.printStackTrace()
            } finally {
                loginUiState = loginUiState.copy(
                    isLoading = false
                )
            }*/
    }
}

data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val userNameSignUp: String = "",
    val passwordSignUp: String = "",
    val confirmPasswordSignUp: String = "",
    val isLoading: Boolean = false,
   // val isSuccessLogin: Boolean = false,
    val signUpError: Boolean? = false,
    val errorSignUpInvalidEmail: Boolean? = false,
    val loginError: Boolean? = false,
    val isRegisterSuccess: Boolean = false
)