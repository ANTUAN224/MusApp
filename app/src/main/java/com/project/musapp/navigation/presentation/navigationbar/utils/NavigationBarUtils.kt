package com.project.musapp.navigation.presentation.navigationbar.utils

import androidx.navigation.NavController
import com.project.musapp.navigation.routing.RouteHub

fun <T : Any> navigateBetweenNavItems(
    route: T,
    navController: NavController
) {
    navController.navigate(route = route) {
        launchSingleTop = true
        restoreState = true
        popUpTo<RouteHub.Home> {
            saveState = true
        }
    }
}