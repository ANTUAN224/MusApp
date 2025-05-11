import org.gradle.kotlin.dsl.testImplementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrainsKotlinSerialization) //Plugin del compilador de Kotlin para la librería de Kotlinx Serialization
}

android {
    namespace = "com.example.musapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.musapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.coil.compose) //Dependencia para utilizar la librería Coil para imágenes locales
    implementation(libs.coil.network.okhttp) //Dependencia de Coil para poder cargar imágenes alojadas en Internet
    implementation (libs.android.lottie) //Dependencia para utilizar la librería Lottie para implementar animaciones profesionales en la app
    implementation(libs.androidx.navigation.compose) //Dependencia para poder utilizar la navegación en Jetpack Compose
    implementation(libs.kotlinx.serialization.json.jvm)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx) //Dependencia para que la clase que se comporte como ViewModel pueda extender la clase ViewModel (imprescindible en MVVM)
    implementation(libs.androidx.lifecycle.livedata) //Dependencia para poder utilizar livedata (imprescindible en MVVM)
    implementation(libs.retrofit) //Dependencia para poder utilizar Retrofit
    implementation(libs.converter.gson) //Dependencia de Retrofit para utilizar la librería Gson para la serialización a JSON y deserialización a objetos
    implementation(libs.androidx.material.icons.extended) //Dependencia para poder utilizar todos los iconos de M3
    implementation(libs.kotlinx.coroutines.core) //Dependencia que proporciona las funciones principales para trabajar con corrutinas
    implementation(libs.kotlinx.coroutines.android) //Dependencia para optimizar las corrutinas con el Main Dispatcher en Android
    testImplementation (libs.kotlinx.coroutines.test) //Dependencia para ejecutar las corrutinas en los tests unitarios sin bloquear el hilo principal
    testImplementation (libs.androidx.core.testing) //Dependencia para realizar pruebas unitarias con ViewModel y LiveData
    implementation(libs.androidx.datastore.preferences) //Dependencia para poder utilizar la librería DataStore Preferences de Jetpack
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}