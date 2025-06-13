package com.project.musapp.core.feature.navigation.item.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _navItemIndex = MutableLiveData<Int>()
    val navItemIndex : LiveData<Int> = _navItemIndex

    private val _showNavBar = MutableLiveData<Boolean>()
    val showNavBar : LiveData<Boolean> = _showNavBar

    fun onHomeScreenNavigation() {
        _showNavBar.value = true
    }

    fun onInitialMenuNavigation() {
        _showNavBar.value = false
    }

    fun onNavItemClick(currentItemIndex : Int) {
        _navItemIndex.value = currentItemIndex
    }
}