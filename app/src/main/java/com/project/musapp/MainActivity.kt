package com.project.musapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.FirebaseApp
import com.project.musapp.core.feature.navigation.item.presentation.ui.MusAppNavigationBar
import com.project.musapp.core.feature.navigation.item.presentation.viewmodel.NavigationViewModel
import com.project.musapp.core.feature.navigation.routing.RouteHub
import com.project.musapp.feature.user.register.presentation.navigation.registrationNavGraph
import com.project.musapp.feature.user.initialChecking.presentation.ui.UserInitialCheckingScreen
import com.project.musapp.feature.user.initialChecking.presentation.viewModel.UserInitialCheckingViewModel
import com.project.musapp.feature.user.initialMenu.presentation.ui.InitialMenuScreen
import com.project.musapp.ui.theme.MusAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint //Permite que se puedan inyectar dependencias dentro de la clase.
class MainActivity : ComponentActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels() //Simplifica la obtención de una instancia de un ViewModel con lazy initialization.

    private val userInitialCheckingViewModel: UserInitialCheckingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Inicialización de Firebase al ejecutar la app
        enableEdgeToEdge()
        setContent {
            MusAppTheme(darkTheme = false) {
                val hasInternetConnection =
                    userInitialCheckingViewModel.hasInternetConnection.observeAsState(initial = null).value

                val hasActiveSession =
                    userInitialCheckingViewModel.hasActiveSession.observeAsState(initial = null).value

                val currentNavItemIndex =
                    navigationViewModel.navItemIndex.observeAsState(initial = 0).value

                val navController = rememberNavController()

                val currentBackStackEntry =
                    navController.currentBackStackEntryAsState().value //Es necesario para la recomposición de Compose

                userInitialCheckingViewModel.executeUserInitialChecking()

                Scaffold(bottomBar = {
                    if (currentBackStackEntry?.toRoute<RouteHub.Home>() == RouteHub.Home.AnyArtworkSearched ||
                        currentBackStackEntry?.toRoute<RouteHub.Home>() == RouteHub.Home.ArtworkSearch
                    )
                        MusAppNavigationBar(
                            navigationViewModel = navigationViewModel,
                            navItemIndex = currentNavItemIndex
                        )
                }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination =
                            if (hasInternetConnection == null && hasActiveSession == null)
                                RouteHub.UserInitialChecking
                            else if (hasInternetConnection == true && hasActiveSession == false) RouteHub.InitialMenu
                            else RouteHub.Home
                    ) {
                        composable<RouteHub.UserInitialChecking> {
                            UserInitialCheckingScreen(
                                viewModel = userInitialCheckingViewModel,
                                hasInternetConnection = hasInternetConnection
                            )
                        }

                        composable<RouteHub.InitialMenu> {
                            InitialMenuScreen(onGoToRegisterButtonPress = {
                                navController.navigate(
                                    route = RouteHub.Registration
                                ) {
                                    popUpTo<RouteHub.InitialMenu> {
                                        inclusive = true
                                    }
                                }
                            })
                            {

                            }
                        }

                        registrationNavGraph(navController, context = applicationContext)
                    }
                }
            }
        }
    }
}