package com.project.musapp.initialchecking.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.core.internetconnectionverification.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.core.sessionstateverification.domain.usecase.VerifyUserSessionStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserStateInitialCheckingViewModel
@Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val verifyUserSessionStateUseCase: VerifyUserSessionStateUseCase
) : ViewModel() {
    private val _hasInternetConnection = MutableLiveData<Boolean?>()
    val hasInternetConnection: LiveData<Boolean?> = _hasInternetConnection

    private val _hasActiveSession = MutableLiveData<Boolean?>()
    val hasActiveSession: LiveData<Boolean?> = _hasActiveSession

    fun onUserInitialChecking() {
        viewModelScope.launch {
            delay(2000) //Simula el tiempo de carga inicial

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()
            }.onSuccess {
                _hasInternetConnection.value = true
                _hasActiveSession.value = verifyUserSessionStateUseCase()
            }.onFailure { throwable ->
                when (throwable) {
                    is InternetConnectionVerificationException.NoInternetConnectionException ->
                        _hasInternetConnection.value = false
                }
            }
        }
    }
}