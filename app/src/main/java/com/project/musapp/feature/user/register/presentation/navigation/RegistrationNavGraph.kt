package com.project.musapp.feature.user.register.presentation.navigation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.project.musapp.core.feature.navigation.item.presentation.viewmodel.NavigationViewModel
import com.project.musapp.core.feature.navigation.routing.RouteHub
import com.project.musapp.feature.user.register.presentation.viewmodel.UserRegisterViewModel
import com.project.musapp.feature.user.register.presentation.ui.UserRegistrationFirstScreen
import com.project.musapp.feature.user.register.presentation.ui.LastRegisterScreen

fun NavGraphBuilder.registrationNavGraph(
    navController: NavController,
    context: Context,
    title : String,
    navigationViewModel: NavigationViewModel
) {
    navigation<RouteHub.Registration>(startDestination = RouteHub.Registration.StepOne) {
        composable<RouteHub.Registration.StepOne> { navBackStackEntry ->
            val userRegisterViewModel: UserRegisterViewModel =
                hiltViewModel(viewModelStoreOwner = navBackStackEntry) //Creo una instancia de ViewModel cuyo scope es el del nav graph actual
            UserRegistrationFirstScreen(
                viewModel = userRegisterViewModel,
                title = title,
                onReturnButtonPress = { navController.popBackStack() }) {
                navController.navigate(route = RouteHub.Registration.StepTwo)
            }
        }

        composable<RouteHub.Registration.StepTwo> { navBackStackEntry ->
            val userRegisterViewModel: UserRegisterViewModel =
                hiltViewModel(viewModelStoreOwner = navBackStackEntry) //Obtengo una instancia de ViewModel cuyo scope es el del nav graph actual

            val navigateToHome by userRegisterViewModel.navigateToHome.observeAsState(initial = false)

            LastRegisterScreen(
                viewModel = userRegisterViewModel,
                context = context,
                title = title,
                onReturnButtonPress = { navController.popBackStack() }) {
                userRegisterViewModel.onLastRegisterScreenButtonPress()

                if (navigateToHome) {
                    navigationViewModel.onHomeScreenNavigation()
                }

                navController.navigate(route = RouteHub.Home) {
                    popUpTo<RouteHub.InitialMenu> { inclusive = true }
                }
            }
        }
    }
}