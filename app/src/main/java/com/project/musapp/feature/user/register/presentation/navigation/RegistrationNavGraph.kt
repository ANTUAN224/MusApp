package com.project.musapp.feature.user.register.presentation.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.project.musapp.core.feature.navigation.routing.RouteHub
import com.project.musapp.feature.user.register.presentation.viewmodel.UserRegisterViewModel
import com.project.musapp.feature.user.register.presentation.ui.FirstRegisterScreen
import com.project.musapp.feature.user.register.presentation.ui.LastRegisterScreen

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

            val navigateToHome =
                userRegisterViewModel.navigateToHome.observeAsState(initial = null).value

            LastRegisterScreen(
                viewModel = userRegisterViewModel,
                context = context,
                onReturnButtonPress = { navController.popBackStack() }) {
                userRegisterViewModel.onLastRegisterScreenButtonPress()

                if (navigateToHome == true) Toast.makeText(
                    context, "Te has registrado correctamente en la base de datos.",
                    Toast.LENGTH_SHORT
                ).show()
//                    navController.navigate(route = RouteHub.Home) {
//                    popUpTo<RouteHub.InitialMenu> { inclusive = true }
//                }
                else if (navigateToHome == false) Toast.makeText(
                    context, "Se ha producido un error durante el proceso de registro",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}