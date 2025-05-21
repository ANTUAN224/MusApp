package com.project.musapp.feature.home.user.register.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.musapp.R
import com.project.musapp.feature.home.user.register.domain.useCase.CreateTokenUseCase
import com.project.musapp.feature.home.user.helper.RegexHelper
import com.project.musapp.feature.home.user.register.domain.model.RegisterUserModel
import com.project.musapp.feature.home.user.register.domain.useCase.UserRegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel

class UserRegisterViewModel(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val createUserTokenUseCase: CreateTokenUseCase
) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _nameError = MutableLiveData<String>()
    val nameError: LiveData<String> = _nameError

    private val _surnames = MutableLiveData<String>()
    val surnames: LiveData<String> = _surnames

    private val _surnamesError = MutableLiveData<String>()
    val surnamesError: LiveData<String> = _surnamesError

    private val _birthdate = MutableLiveData<LocalDate>()
    val birthdate: LiveData<LocalDate> = _birthdate

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _imagePath = MutableLiveData<Uri>()
    val imagePath: LiveData<Uri> = _imagePath

    private val _isImageSelected = MutableLiveData<Boolean>()
    val isImageSelected: LiveData<Boolean> = _isImageSelected

    private val _isFirstRegisterScreenButtonEnabled = MutableLiveData<Boolean>()
    val isFirstResgisterScreenButtonEnabled: LiveData<Boolean> = _isFirstRegisterScreenButtonEnabled

    private val _isLastRegisterScreenButtonEnabled = MutableLiveData<Boolean>()
    val isLastResgisterScreenButtonEnabled: LiveData<Boolean> = _isLastRegisterScreenButtonEnabled

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun onNameTextFieldChange(name: String) {
        _name.value = name
        setNameErrorMessage(name)
        checkFirstRegisterScreenErrors()
    }

    fun onSurnameTextFieldChange(surnames: String) {
        _surnames.value = surnames
        setSurnameErrorMessage(surnames)
        checkFirstRegisterScreenErrors()
    }

    private fun setNameErrorMessage(name: String) {
        _nameError.value = when {
            name.isBlank() -> "El nombre no puede estar en blanco."
            RegexHelper.nameRegex.matches(name) -> "El nombre no tiene un formato correcto."
            else -> ""
        }
    }

    private fun setSurnameErrorMessage(surnames: String) {
        _surnamesError.value = when {
            surnames.isBlank() -> "Los apellidos no pueden estar en blanco."
            RegexHelper.nameRegex.matches(surnames) -> "Los apellidos no tienen un formato correcto."
            else -> ""
        }
    }

    private fun checkFirstRegisterScreenErrors() {
        _isFirstRegisterScreenButtonEnabled.value =
            nameError.value!!.isNotBlank() && surnamesError.value!!.isNotBlank()
    }

    fun onEmailTextFieldChange(email: String) {
        _email.value = email
        setEmailErrorMessage(email)
        checkLastRegisterScreenErrors()
    }

    private fun setEmailErrorMessage(email: String) {
        _nameError.value = when {
            email.isBlank() -> "El email no puede estar en blanco."
            RegexHelper.emailRegex.matches(email) -> "El email no tiene un formato correcto."
            else -> ""
        }
    }

    fun onPasswordTextFieldChange(password: String) {
        _password.value = password
        setPasswordErrorMessage(password)
        checkLastRegisterScreenErrors()
    }

    private fun setPasswordErrorMessage(password: String) {
        _passwordError.value = when {
            password.isBlank() -> "La contraseña no pueden estar en blanco."
            RegexHelper.passwordRegex.matches(password) -> "La contraseña no tiene un formato correcto."
            else -> ""
        }
    }

    private fun checkLastRegisterScreenErrors() =
        emailError.value!!.isBlank() && passwordError.value!!.isBlank()

    fun onImageSelect(imagePathSelected: Uri) {
        _imagePath.value = imagePathSelected
    }

    fun onImageDelete(context: Context) {
        _imagePath.value =
            "android.resource://${context.packageName}/${R.drawable.default_image}".toUri()
    }

    fun onRegisterScreenButtonPressed() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val isSuccessful = userRegisterUseCase(
                registerUserModel = RegisterUserModel(
                    name = name.value!!,
                    surnames = surnames.value!!,
                    birthdate = birthdate.value!!,
                    email = email.value!!,
                    password = password.value!!,
                    imagePath = imagePath.value!!
                )
            ) && createUserTokenUseCase(email = email.value!!, password = password.value!!)

            withContext(context = Dispatchers.Main) {
                _navigateToHome.value = isSuccessful
            }
        }
    }
}