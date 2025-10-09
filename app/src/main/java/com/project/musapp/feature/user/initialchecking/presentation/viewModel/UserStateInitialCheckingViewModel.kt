package com.project.musapp.feature.user.initialchecking.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.feature.user.initialchecking.domain.usecase.UserConnectionVerificationUseCase
import com.project.musapp.feature.user.initialchecking.domain.usecase.UserSessionVerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserStateInitialCheckingViewModel
@Inject constructor(
    private val userConnectionVerificationUseCase: UserConnectionVerificationUseCase,
    private val userSessionVerificationUseCase: UserSessionVerificationUseCase
) : ViewModel() {
    private val _hasInternetConnection = MutableLiveData<Boolean?>()
    val hasInternetConnection: LiveData<Boolean?> = _hasInternetConnection

    private val _hasActiveSession = MutableLiveData<Boolean?>()
    val hasActiveSession: LiveData<Boolean?> = _hasActiveSession

    fun onUserInitialChecking() {
        viewModelScope.launch(context = Dispatchers.IO) {
            delay(2000) //Simula el tiempo de carga inicial
            withContext(context = Dispatchers.Main) {
                _hasInternetConnection.value = userConnectionVerificationUseCase()
                _hasActiveSession.value = userSessionVerificationUseCase()
            }
        }
    }
}