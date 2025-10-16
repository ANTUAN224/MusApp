package com.project.musapp.feature.user.registration.presentation.navigation

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.project.musapp.core.feature.navigation.item.presentation.viewmodel.NavigationViewModel
import com.project.musapp.core.feature.navigation.routing.RouteHub
import com.project.musapp.feature.user.registration.presentation.viewmodel.UserRegistrationViewModel
import com.project.musapp.feature.user.registration.presentation.ui.UserRegistrationFirstScreen
import com.project.musapp.feature.user.registration.presentation.ui.LastRegistrationScreen

fun NavGraphBuilder.registrationNavGraph(
    navController: NavController,
    context: Context,
    title: String,
    navigationViewModel: NavigationViewModel
) {
    navigation<RouteHub.Registration>(startDestination = RouteHub.Registration.StepOne) {
        composable<RouteHub.Registration.StepOne> { navBackStackEntry ->
            val userRegistrationViewModel: UserRegistrationViewModel =
                hiltViewModel(viewModelStoreOwner = navBackStackEntry) //Creo una instancia de ViewModel cuyo scope es el del nav graph actual
            UserRegistrationFirstScreen(
                viewModel = userRegistrationViewModel,
                title = title,
                onReturnButtonPress = { navController.popBackStack() }) {
                navController.navigate(route = RouteHub.Registration.StepTwo)
            }
        }

        composable<RouteHub.Registration.StepTwo> { navBackStackEntry ->
            val userRegistrationViewModel: UserRegistrationViewModel =
                hiltViewModel(viewModelStoreOwner = navBackStackEntry) //Obtengo una instancia de ViewModel cuyo scope es el del nav graph actual

            val isLoading by userRegistrationViewModel.isLoading.observeAsState(initial = false)

            val navigateToHome by userRegistrationViewModel.navigateToHome.observeAsState(initial = null)

            LastRegistrationScreen(
                viewModel = userRegistrationViewModel,
                context = context,
                title = title,
                isLoading = isLoading,
                navigateToHome = navigateToHome,
                onReturnButtonPress = { navController.popBackStack() },
                onLastRegisterButtonPress = {
                    userRegistrationViewModel.onLastRegisterScreenButtonPress()
                }) {
                navController.navigate(route = RouteHub.UserStateInitialChecking) {
                    popUpTo<RouteHub.Registration> { inclusive = true }
                }
            }

            LaunchedEffect(navigateToHome) {
                if (navigateToHome == true) {
                    navigationViewModel.onHomeScreenNavigation()

                    navController.navigate(route = RouteHub.Home) {
                        popUpTo<RouteHub.Registration> { inclusive = true }
                    }
                }
            }
        }
    }
}