package com.project.musapp.initialchecking.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.musapp.core.component.presentation.ui.CommonCircularProgressIndicator
import com.project.musapp.core.component.presentation.ui.CommonNoInternetConnectionModal

@Composable
fun UserStateInitialCheckingScreen(
    hasInternetConnection: Boolean?
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CommonCircularProgressIndicator()
        if (hasInternetConnection == false) CommonNoInternetConnectionModal()
    }
}