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

    private val _isArrivingForFirstTimeToCollection = MutableLiveData<Boolean>()
    val isArrivingForFirstTimeToCollection: LiveData<Boolean> = _isArrivingForFirstTimeToCollection

    private val _isArrivingForFirstTimeToArtisticCulture = MutableLiveData<Boolean>()
    val isArrivingForFirstTimeToArtisticCulture: LiveData<Boolean> =
        _isArrivingForFirstTimeToArtisticCulture

    private val _hasArtworkBeenNavigatedFromCollection = MutableLiveData<Boolean>()
    val hasArtworkBeenNavigatedFromCollection: LiveData<Boolean> =
        _hasArtworkBeenNavigatedFromCollection

    fun onNavBarShowing() {
        _showNavBar.value = true
    }

    fun onNavBarHiding() {
        _showNavBar.value = false
    }

    fun onUserLogOut() {
        _showNavBar.value = false
        _isArrivingForFirstTimeToCollection.value = true
        _isArrivingForFirstTimeToArtisticCulture.value = true
    }

    fun onCollectionFirstTimeArrival() {
        viewModelScope.launch {
            delay(3000)

            _isArrivingForFirstTimeToCollection.value = false
        }
    }

    fun onArtworkArrivalFromCollection() {
        _hasArtworkBeenNavigatedFromCollection.value = true
    }

    fun onCollectionArrival() {
        viewModelScope.launch {
            delay(3000)

            _hasArtworkBeenNavigatedFromCollection.value = false
        }
    }

    fun onArtisticCultureFirstTimeArrival() {
        viewModelScope.launch {
            delay(3000)

            _isArrivingForFirstTimeToArtisticCulture.value = false
        }
    }

    fun onNavItemClick(currentNavItemIndex: Int) {
        _navItemIndex.value = currentNavItemIndex
    }
}