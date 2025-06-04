package com.project.musapp.core.feature.navigation.item.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _navItemIndex = MutableLiveData<Int>()
    val navItemIndex = _navItemIndex

    fun onNavItemClick(currentItemIndex : Int) {
        _navItemIndex.value = currentItemIndex
    }
}