package com.project.musapp.feature.user.auth.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.feature.user.auth.helper.RegisterOrLoginRegexHelper
import com.project.musapp.feature.user.auth.login.domain.useCase.VerifyUserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val verifyUserLoginUseCase: VerifyUserLoginUseCase
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

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun onLoginModalOpening() {
        _showLoginModal.value = true
    }

    fun onLoginModalClosing() {
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

    fun checkUserLogin() {
        viewModelScope.launch {

        }
    }
}