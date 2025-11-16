package com.project.musapp.navigation.presentation.splashscreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.core.network.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.feature.auth.domain.usecase.VerifyUserSessionStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
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
                viewModelScope.launch {
                    verifyUserSessionStateUseCase().collect { user ->
                        _hasActiveSession.value = user != null
                    }
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _hasInternetConnection.value = false
                }

                Log.d("EJECUCIÓN", "Error en el chequeo inicial -> $throwable")
            }
        }
    }
}