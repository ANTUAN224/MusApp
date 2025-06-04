package com.project.musapp.feature.user.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.feature.user.helper.RegisterOrLoginRegexHelper
import com.project.musapp.feature.user.login.domain.useCase.VerifyUserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(private val useCase: VerifyUserLoginUseCase) :
    ViewModel() {
    private val _showLoginModal = MutableLiveData<Boolean>()
    val showLoginModal: LiveData<Boolean> = _showLoginModal

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _isLoginButtonEnabled = MutableLiveData<Boolean>()
    val isLoginButtonEnabled: LiveData<Boolean> = _isLoginButtonEnabled

    private val _userNotFoundInDatabaseMessage = MutableLiveData<String?>()
    val userNotFoundInDatabaseMessage: LiveData<String?> = _userNotFoundInDatabaseMessage

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
            !RegisterOrLoginRegexHelper.passwordRegex.matches(password) -> "La contraseña no tiene un formato correcto."
            else -> ""
        }
    }

    private fun checkLoginFields() {
        _isLoginButtonEnabled.value =
            email.value?.isBlank() == true && password.value?.isBlank() == true
    }

    fun checkUserLogin() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val userExistsInDatabase = useCase(email = email.value!!, password = password.value!!)
            withContext(context = Dispatchers.Main) {
                if (!userExistsInDatabase) {
                    _userNotFoundInDatabaseMessage.value =
                        "El usuario con esas credenciales no existe en la base de datos."
                    _navigateToHome.value = true
                } else {
                    _userNotFoundInDatabaseMessage.value = ""
                    _navigateToHome.value = false
                }
            }
        }
    }
}