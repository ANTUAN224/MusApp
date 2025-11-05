package com.project.musapp.feature.user.auth.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.core.internetconnectionverification.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.feature.user.auth.helper.RegisterOrLoginRegexHelper
import com.project.musapp.feature.user.auth.login.domain.exception.UserLoginException
import com.project.musapp.feature.user.auth.login.domain.useCase.LogInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val logInUserUseCase: LogInUserUseCase
) :
    ViewModel() {
    private val _showLoginModal = MutableLiveData<Boolean>()
    val showLoginModal: LiveData<Boolean> = _showLoginModal

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _showPassword = MutableLiveData<Boolean>()
    val showPassword: LiveData<Boolean> = _showPassword

    private val _isLoginAcceptButtonEnabled = MutableLiveData<Boolean>()
    val isLoginAcceptButtonEnabled: LiveData<Boolean> = _isLoginAcceptButtonEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _navigateToHome = MutableLiveData<Boolean?>()
    val navigateToHome: LiveData<Boolean?> = _navigateToHome

    private val _showNoInternetConnectionModal = MutableLiveData<Boolean>()
    val showNoInternetConnectionModal: LiveData<Boolean> = _showNoInternetConnectionModal

    private val _showUserNotFoundModal = MutableLiveData<Boolean>()
    val showUserNotFoundModal: LiveData<Boolean> = _showUserNotFoundModal

    fun onLoginModalOpening() {
        _showLoginModal.value = true
    }

    fun onLoginModalClosing() {
        _email.value = ""
        _password.value = ""
        _emailError.value = ""
        _passwordError.value = ""
        _showPassword.value = false
        _showLoginModal.value = false
    }

    fun onEmailChange(email: String) {
        _email.value = email
        setEmailErrorMessage(email)
        checkLoginFields()
    }


    private fun setEmailErrorMessage(email: String) {
        _emailError.value = when {
            email.isBlank() -> "El email no puede estar en blanco."
            !RegisterOrLoginRegexHelper.emailRegex.matches(email) -> "El email no tiene un formato correcto."
            email.length > 30 -> "El email no puede tener más de 30 caracteres."
            else -> ""
        }
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        setPasswordErrorMessage(password)
        checkLoginFields()
    }

    private fun setPasswordErrorMessage(password: String) {
        _passwordError.value = when {
            password.isBlank() -> "La contraseña no pueden estar en blanco."
            password.length < 8 -> "La contraseña debe tener al menos 8 caracteres."
            !RegisterOrLoginRegexHelper.passwordRegex.matches(password) -> "La contraseña no tiene un formato correcto."
            password.length > 30 -> "La contraseña no puede tener más de 30 caracteres."
            else -> ""
        }
    }

    private fun checkLoginFields() {
        _isLoginAcceptButtonEnabled.value =
            emailError.value?.isBlank() ?: false && passwordError.value?.isBlank() ?: false
    }

    fun onPasswordShowingStateChange(currentShowingPasswordState: Boolean) {
        _showPassword.value = !currentShowingPasswordState
    }

    fun onUserNotFoundModalClosing() {
        _navigateToHome.value = null
        _showUserNotFoundModal.value = false
        _showLoginModal.value = false
    }

    fun onLoginAcceptButtonClick() {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    logInUserUseCase(email = email.value!!, password.value!!).getOrThrow()
                }
            }.onSuccess {
                _navigateToHome.value = true
            }.onFailure { throwable ->
                when (throwable) {
                    is InternetConnectionVerificationException ->
                        _showNoInternetConnectionModal.value = true

                    is UserLoginException.UserNotFoundException ->
                        _showUserNotFoundModal.value = true
                }

                _navigateToHome.value = false
            }

            _isLoading.value = false
        }
    }
}