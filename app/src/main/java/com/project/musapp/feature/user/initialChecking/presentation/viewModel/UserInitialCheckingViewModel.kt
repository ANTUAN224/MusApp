package com.project.musapp.feature.user.initialChecking.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.feature.user.initialChecking.domain.usecase.UserConnectionVerificationUseCase
import com.project.musapp.feature.user.initialChecking.domain.usecase.UserSessionVerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.measureTime

@HiltViewModel
class UserInitialCheckingViewModel
@Inject constructor(
    private val userConnectionVerificationUseCase: UserConnectionVerificationUseCase,
    private val userSessionVerificationUseCase: UserSessionVerificationUseCase
) : ViewModel() {
    private val _hasInternetConnection = MutableLiveData<Boolean?>()
    val hasInternetConnection: LiveData<Boolean?> = _hasInternetConnection

    private val _hasActiveSession = MutableLiveData<Boolean?>()
    val hasActiveSession: LiveData<Boolean?> = _hasActiveSession

    fun executeUserInitialChecking() {
        viewModelScope.launch(context = Dispatchers.IO) {
            var hasInternetConnection: Boolean
            var hasActiveSession: Boolean

            measureTime {
                hasInternetConnection = userConnectionVerificationUseCase()
                hasActiveSession = userSessionVerificationUseCase()
            }
            delay(2000) //Por si la carga inicial es rápida
            withContext(context = Dispatchers.Main) {
                _hasInternetConnection.value = hasInternetConnection
                _hasActiveSession.value = hasActiveSession
            }
        }
    }
}