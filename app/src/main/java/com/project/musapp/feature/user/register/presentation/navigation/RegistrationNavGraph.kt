package com.project.musapp.feature.user.register.presentation.navigation

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.project.musapp.core.feature.navigation.routing.RouteHub
import com.project.musapp.feature.user.register.presentation.ui.FirstRegisterScreen
import com.project.musapp.feature.user.register.presentation.ui.LastRegisterScreen
import com.project.musapp.feature.user.register.presentation.viewmodel.UserRegisterViewModel

fun NavGraphBuilder.registrationNavGraph(navController: NavController, context: Context) {
    navigation<RouteHub.Registration>(startDestination = RouteHub.Registration.StepOne) {
        composable<RouteHub.Registration.StepOne> { navBackStackEntry ->
            val userRegisterViewModel: UserRegisterViewModel =
                hiltViewModel(viewModelStoreOwner = navBackStackEntry) //Creo una instancia de ViewModel cuyo scope es el del nav graph actual
            FirstRegisterScreen(
                viewModel = userRegisterViewModel,
                onReturnButtonPress = { navController.popBackStack() }) {
                navController.navigate(route = RouteHub.Registration.StepTwo)
            }
        }

        composable<RouteHub.Registration.StepTwo> { navBackStackEntry ->
            val userRegisterViewModel: UserRegisterViewModel =
                hiltViewModel(viewModelStoreOwner = navBackStackEntry) //Obtengo una instancia de ViewModel cuyo scope es el del nav graph actual
            LastRegisterScreen(
                viewModel = userRegisterViewModel,
                context = context,
                onReturnButtonPress = { navController.popBackStack() }) {
                navController.navigate(route = RouteHub.Home)
            }
        }
    }
}