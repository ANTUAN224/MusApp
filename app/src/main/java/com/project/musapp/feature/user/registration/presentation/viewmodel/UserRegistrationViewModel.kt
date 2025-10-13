package com.project.musapp.feature.user.registration.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.R
import com.project.musapp.feature.user.helper.RegisterOrLoginRegexHelper
import com.project.musapp.feature.user.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.registration.domain.usecase.CreateFirebaseUserUseCase
import com.project.musapp.feature.user.registration.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class UserRegistrationViewModel @Inject constructor(
    private val createFirebaseUserUseCase: CreateFirebaseUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _nameError = MutableLiveData<String>()
    val nameError: LiveData<String> = _nameError

    private val _surnames = MutableLiveData<String>()
    val surnames: LiveData<String> = _surnames

    private val _surnamesError = MutableLiveData<String>()
    val surnamesError: LiveData<String> = _surnamesError

    private val _showDatePickerDialog = MutableLiveData<Boolean>()
    val showDatePickerDialog = _showDatePickerDialog

    private val _birthdateText = MutableLiveData<String>()
    val birthdateText: LiveData<String> = _birthdateText

    private val _birthdateTextError = MutableLiveData<String>()
    val birthdateError: LiveData<String> = _birthdateTextError

    private val _showPassword = MutableLiveData<Boolean>()
    val showPassword: LiveData<Boolean> = _showPassword

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

    private val _isUserRegistrationFirstScreenButtonEnabled = MutableLiveData<Boolean>()
    val isUserRegistrationFirstScreenButtonEnabled: LiveData<Boolean> =
        _isUserRegistrationFirstScreenButtonEnabled

    private val _isUserRegistrationLastScreenButtonEnabled = MutableLiveData<Boolean>()
    val isUserRegistrationLastScreenButtonEnabled: LiveData<Boolean> =
        _isUserRegistrationLastScreenButtonEnabled

    private val _isImageSelectionButtonPressed = MutableLiveData<Boolean>()
    val isImageSelectionButtonPressed: LiveData<Boolean> = _isImageSelectionButtonPressed

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun onNameChange(name: String) {
        _name.value = name
        setNameErrorMessage(name)
        checkUserRegistrationFirstScreenErrors()
    }

    fun onSurnamesChange(surnames: String) {
        _surnames.value = surnames
        setSurnameErrorMessage(surnames)
        checkUserRegistrationFirstScreenErrors()
    }

    fun onBirthdateTextChange(birthdateText: String) {
        _birthdateText.value = birthdateText
        setBirthDateErrorMessage(birthdateText)
        checkUserRegistrationFirstScreenErrors()
    }

    private fun setNameErrorMessage(name: String) {
        _nameError.value = when {
            name.isBlank() -> "El nombre no puede estar en blanco."
            !RegisterOrLoginRegexHelper.nameOrSurnameRegex.matches(name) -> "El nombre no tiene un formato correcto."
            name.length > 40 -> "El nombre no puede tener más de 40 caracteres."
            else -> ""
        }
    }

    private fun setSurnameErrorMessage(surnames: String) {
        _surnamesError.value = when {
            surnames.isBlank() -> "Los apellidos no pueden estar en blanco."
            !RegisterOrLoginRegexHelper.nameOrSurnameRegex.matches(surnames) -> "Los apellidos no tienen un formato correcto."
            surnames.length > 70 -> "Los apellidos no pueden tener más de 70 caracteres."
            else -> ""
        }
    }

    private fun setBirthDateErrorMessage(birthdateText: String) {
        _birthdateTextError.value = when {
            birthdateText.isBlank() -> "La fecha no puede estar en blanco."
            ChronoUnit.YEARS.between(
                LocalDate.parse(
                    birthdateText
                ), LocalDate.now()
            ) < 18 -> "Debes ser mayor de edad para poder registrarte"

            else -> ""
        }
    }

    private fun checkUserRegistrationFirstScreenErrors() {
        _isUserRegistrationFirstScreenButtonEnabled.value =
            nameError.value?.isBlank() ?: false && birthdateError.value?.isBlank() ?: false && surnamesError.value?.isBlank() ?: false
    }

    fun onEmailChange(email: String) {
        _email.value = email
        setEmailErrorMessage(email)
        checkUserRegistrationLastScreenErrors()
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
        checkUserRegistrationLastScreenErrors()
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

    private fun checkUserRegistrationLastScreenErrors() {
        _isUserRegistrationLastScreenButtonEnabled.value =
            emailError.value?.isBlank() ?: false && passwordError.value?.isBlank() ?: false
    }

    fun setSelectedImage(imagePathSelected: Uri?) {
        _isImageSelected.value = imagePathSelected != null
        _imagePath.value = imagePathSelected
            ?: "android.resource://${context.packageName}/${R.drawable.default_image}".toUri()
        _isImageSelectionButtonPressed.value = false
    }

    fun onImageDeletion(context: Context) {
        _isImageSelected.value = false
        _imagePath.value =
            "android.resource://${context.packageName}/${R.drawable.default_image}".toUri()
    }

    fun onDatePickerDialogOpening() {
        _showDatePickerDialog.value = true
    }

    fun onDatePickerDialogClosing() {
        _showDatePickerDialog.value = false
    }

    fun onPasswordShowingStateChange(currentShowingPasswordState: Boolean) {
        _showPassword.value = !currentShowingPasswordState
    }

    fun onImageSelectionButtonPress() {
        _isImageSelectionButtonPressed.value = true
    }

    fun onLastRegisterScreenButtonPress() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val isSuccessful = createFirebaseUserUseCase(
                email = email.value!!,
                password = password.value!!
            ) && registerUserUseCase(
                userRegistrationModel = UserRegistrationModel(
                    name = name.value!!,
                    surnames = surnames.value!!,
                    birthdateText = birthdateText.value!!,
                    email = email.value!!,
                    profileImageLocalPath = imagePath.value!!
                )
            )
            withContext(context = Dispatchers.Main) {
                _navigateToHome.value = isSuccessful
            }
        }
    }
}