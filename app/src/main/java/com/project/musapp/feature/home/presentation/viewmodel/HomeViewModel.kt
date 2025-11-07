package com.project.musapp.feature.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.core.network.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.feature.artwork.domain.usecase.GetUserFavoriteArtworksUseCase
import com.project.musapp.feature.auth.domain.usecase.LogOutUserUseCase
import com.project.musapp.feature.profile.domain.usecase.GetUserProfileUseCase
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.feature.profile.presentation.model.UserProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserFavoriteArtworksUseCase: GetUserFavoriteArtworksUseCase,
    private val logOutUserUseCase: LogOutUserUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isFirstDropdownMenuExpanded = MutableLiveData<Boolean>()
    val isFirstDropdownMenuExpanded: LiveData<Boolean> = _isFirstDropdownMenuExpanded

    private val _isSettingDropdownMenuExpanded = MutableLiveData<Boolean>()
    val isSettingDropdownMenuExpanded: LiveData<Boolean> = _isSettingDropdownMenuExpanded

    private val _isProfileDropdownMenuExpanded = MutableLiveData<Boolean>()
    val isProfileDropdownMenuExpanded: LiveData<Boolean> = _isProfileDropdownMenuExpanded

    private val _showNoInternetConnectionModal = MutableLiveData<Boolean>()
    val showNoInternetConnectionModal: LiveData<Boolean> = _showNoInternetConnectionModal

    private val _userProfile = MutableLiveData<UserProfileUiModel>()
    val userProfile: LiveData<UserProfileUiModel> = _userProfile

    private val _userFavoriteArtworks = MutableLiveData<List<ArtworkPreviewUiModel>>()
    val userFavoriteArtworks: LiveData<List<ArtworkPreviewUiModel>> = _userFavoriteArtworks

    private val _isDarkModeActivated = MutableLiveData<Boolean>()
    val isDarkModeActivated: LiveData<Boolean> = _isDarkModeActivated

    private val _showContactWithSupportModal = MutableLiveData<Boolean>()
    val showContactWithSupportModal: LiveData<Boolean> = _showContactWithSupportModal

    private val _showUserProfileModal = MutableLiveData<Boolean>()
    val showUserProfileModal: LiveData<Boolean> = _showUserProfileModal

    fun onFirstDropdownMenuExpansion() {
        _isFirstDropdownMenuExpanded.value = true
    }

    fun onFirstDropdownMenuCollapse() {
        _isFirstDropdownMenuExpanded.value = false
    }

    fun onSettingDropdownMenuExpansion() {
        _isSettingDropdownMenuExpanded.value = true
    }

    fun onSettingDropdownMenuCollapse() {
        _isSettingDropdownMenuExpanded.value = false
    }

    fun onSwitchClick(checkedState: Boolean) {
        _isDarkModeActivated.value = checkedState
    }

    fun onContactWithSupportModalOpening() {
        _showContactWithSupportModal.value = true
    }

    fun onContactWithSupportModalClosing() {
        _showContactWithSupportModal.value = false
    }

    fun onUserProfileDropdownMenuExpansion() {
        _isProfileDropdownMenuExpanded.value = true
    }

    fun onUserProfileDropdownMenuCollapse() {
        _isProfileDropdownMenuExpanded.value = false
    }

    fun onUserProfileModalOpening() {
        _showUserProfileModal.value = true
    }

    fun onUserProfileModalClosing() {
        _showUserProfileModal.value = false
    }

    fun getHomeData() {
        viewModelScope.launch {
            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _userProfile.postValue(getUserProfileUseCase().getOrThrow())
                    _userFavoriteArtworks.postValue(getUserFavoriteArtworksUseCase().getOrThrow())
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }
            }

            _isLoading.value = false
        }
    }

    fun logOutUser() {
        onUserProfileDropdownMenuCollapse()
        onFirstDropdownMenuCollapse()
        logOutUserUseCase()
    }
}