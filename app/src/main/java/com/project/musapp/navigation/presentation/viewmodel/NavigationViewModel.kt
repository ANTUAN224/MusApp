package com.project.musapp.navigation.presentation.viewmodel

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

    private val _hasArtworkBeenNavigatedFromCollection = MutableLiveData<Boolean>()
    val hasArtworkBeenNavigatedFromCollection: LiveData<Boolean> =
        _hasArtworkBeenNavigatedFromCollection

    private val _hasArtworkBeenAddedToCollections = MutableLiveData<Boolean>()
    val hasArtworkBeenAddedToCollections: LiveData<Boolean> = _hasArtworkBeenAddedToCollections

    private val _hasArtworkBeenDeletedFromCollections = MutableLiveData<Boolean>()
    val hasArtworkBeenDeletedFromCollections: LiveData<Boolean> =
        _hasArtworkBeenDeletedFromCollections

    private val _hasArtworkBeenMarkedAsFavorite = MutableLiveData<Boolean>()
    val hasArtworkBeenMarkedAsFavorite: LiveData<Boolean> = _hasArtworkBeenMarkedAsFavorite

    fun onNavBarShowing() {
        _showNavBar.value = true
    }

    fun onNavBarHiding() {
        _showNavBar.value = false
    }

    fun onUserLogOut() {
        _showNavBar.value = false
        _isArrivingForFirstTimeToCollection.value = true
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

    fun onReturnToCollectionPreviews() {
        _hasArtworkBeenNavigatedFromCollection.value = false
    }

    fun onCollectionArrival() {
        viewModelScope.launch {
            delay(3000)

            _hasArtworkBeenNavigatedFromCollection.value = false
        }
    }

    fun onArtworkMarkedAsFavoriteStateChange(hasArtworkBeenMarkedAsFavorite: Boolean) {
        _hasArtworkBeenMarkedAsFavorite.value = hasArtworkBeenMarkedAsFavorite
    }

    fun onArtworkAdditionToCollections() {
        _hasArtworkBeenAddedToCollections.value = true
    }

    fun onHomeArrival() {
        if (hasArtworkBeenMarkedAsFavorite.value != null) {
            _hasArtworkBeenMarkedAsFavorite.value = null
        }

        if (hasArtworkBeenAddedToCollections.value ?: false) {
            _hasArtworkBeenAddedToCollections.value = false
        } else if (hasArtworkBeenDeletedFromCollections.value ?: false) {
            _hasArtworkBeenDeletedFromCollections.value = false
        }
    }

    fun onArtworkDeletionFromCollections() {
        _hasArtworkBeenDeletedFromCollections.value = true
    }

    fun onNavItemClick(currentNavItemIndex: Int) {
        _navItemIndex.value = currentNavItemIndex
    }
}