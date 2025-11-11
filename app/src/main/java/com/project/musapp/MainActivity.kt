package com.project.musapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.google.firebase.FirebaseApp
import com.project.musapp.navigation.presentation.entrypoint.NavigationEntryPoint
import com.project.musapp.ui.theme.MusAppTheme
import dagger.hilt.android.AndroidEntryPoint

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