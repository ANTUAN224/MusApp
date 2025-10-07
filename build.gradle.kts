// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.services) apply false // Plugin de Google Services necesario para que Firebase tenga una configuración automática
    alias(libs.plugins.ksp) apply false // Plugin de KSP (Kotlin Symbol Processing) para poder generar código a partir de diversas librerías
    alias(libs.plugins.hilt.android) apply false //Plugin de Google para utilizar la librería Dagger Hilt
}
