// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false // Plugin de Google Services necesario para que Firebase tenga una configuración automática
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false // Plugin de KSP (Kotlin Symbol Processing) Para poder utilizar las anotacioes en Room
    id ("com.google.dagger.hilt.android") version "2.56.2" apply false //Plugin de Google para utilizar la librería Dagger Hilt
}