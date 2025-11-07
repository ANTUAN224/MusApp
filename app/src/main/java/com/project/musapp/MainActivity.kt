package com.project.musapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.project.musapp.feature.auth.presentation.login.ui.LoginModal
import com.project.musapp.feature.auth.presentation.login.ui.UserNotFoundModal
import com.project.musapp.feature.auth.presentation.login.viewmodel.UserLoginViewModel
import com.project.musapp.feature.auth.presentation.registration.ui.EmailAlreadyInUseModal
import com.project.musapp.feature.auth.presentation.registration.ui.UserRegistrationScreen
import com.project.musapp.feature.auth.presentation.registration.viewmodel.UserRegistrationViewModel
import com.project.musapp.ui.commoncomponents.CommonLoadingScreen
import com.project.musapp.ui.commoncomponents.CommonNoInternetConnectionModal
import com.project.musapp.navigation.presentation.navigationbar.ui.MusAppNavigationBar
import com.project.musapp.navigation.presentation.navigationbar.viewmodel.NavigationViewModel
import com.project.musapp.navigation.routing.RouteHub
import com.project.musapp.feature.home.presentation.ui.HomeScreen
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.navigation.presentation.entrypoint.NavigationEntryPoint
import com.project.musapp.navigation.presentation.initialmenu.ui.InitialMenuScreen
import com.project.musapp.navigation.presentation.splashscreen.viewModel.SplashScreenViewModel
import com.project.musapp.ui.theme.MusAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint //Permite que se puedan inyectar dependencias dentro de la clase.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Inicialización de Firebase al ejecutar la app
        enableEdgeToEdge()
        setContent {
            MusAppTheme(darkTheme = false) {
                NavigationEntryPoint(applicationContext = applicationContext)
            }
        }
    }
}