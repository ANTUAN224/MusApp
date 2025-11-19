package com.project.musapp.navigation.presentation.navigationbar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _navItemIndex = MutableLiveData<Int>()
    val navItemIndex: LiveData<Int> = _navItemIndex

    private val _showNavBar = MutableLiveData<Boolean>()
    val showNavBar: LiveData<Boolean> = _showNavBar

    private val _isArrivingForFirstTimeToHome = MutableLiveData<Boolean>()
    val isArrivingForFirstTimeToHome: LiveData<Boolean> = _isArrivingForFirstTimeToHome

    private val _isArrivingForFirstTimeToCollection = MutableLiveData<Boolean>()
    val isArrivingForFirstTimeToCollection: LiveData<Boolean> = _isArrivingForFirstTimeToCollection

    private val _isArrivingForFirstTimeToArtisticCulture = MutableLiveData<Boolean>()
    val isArrivingForFirstTimeToArtisticCulture: LiveData<Boolean> = _isArrivingForFirstTimeToArtisticCulture

    private val _hasArtworkBeenMarkedAsFavorite = MutableLiveData<Boolean?>()
    val hasArtworkBeenMarkedAsFavorite: LiveData<Boolean?> = _hasArtworkBeenMarkedAsFavorite

    fun onNavBarShowing() {
        _showNavBar.value = true
    }

    fun onNavBarHiding() {
        _showNavBar.value = false
    }

    fun onUserLogOut() {
        _showNavBar.value = false
        _isArrivingForFirstTimeToHome.value = true
        _isArrivingForFirstTimeToCollection.value = true
        _isArrivingForFirstTimeToCollection.value = true
        _hasArtworkBeenMarkedAsFavorite.value = null
    }

    fun onHomeFirstTimeArrival() {
        _isArrivingForFirstTimeToHome.value = false
    }

    fun onCollectionFirstTimeArrival() {
        viewModelScope.launch {
            delay(3000)

            _isArrivingForFirstTimeToCollection.value = false
        }
    }

    fun onArtisticCultureFirstTimeArrival() {
        viewModelScope.launch {
            delay(3000)

            _isArrivingForFirstTimeToArtisticCulture.value = false
        }
    }

    fun onArtworkMarkedAsFavoriteStateChange(hasArtworkBeenMarkedAsFavorite: Boolean) {
        _hasArtworkBeenMarkedAsFavorite.value = hasArtworkBeenMarkedAsFavorite
    }

    fun onNavItemClick(currentNavItemIndex: Int) {
        _navItemIndex.value = currentNavItemIndex
    }
}