
import org.gradle.kotlin.dsl.testImplementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrainsKotlinSerialization) //Plugin del compilador de Kotlin para la librería de Kotlinx Serialization
    id("com.google.gms.google-services") //Plugin de Google a nivel de módulo Services necesario para que Firebase tenga una configuración automática
    id("com.google.devtools.ksp") //Plugin de KSP (Kotlin Symbol Processing) a nivel de módulo necesario para utilizar las anotaciones de Room
    id ("com.google.dagger.hilt.android") //Plugin de Google para utilizar la librería Dagger Hilt
}

android {
    namespace = "com.project.musapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.project.musapp"
        minSdk = 26 //SDK mínimo para poder utilizar LocalDate.parse()
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform() //Permite ejecutar tests con JUnit 5
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
    implementation(libs.androidx.runtime.livedata) //Dependencia para poder utilizar la función 'observeAsState()' de LiveData
    implementation(libs.retrofit) //Dependencia para poder utilizar Retrofit
    implementation(libs.converter.gson) //Dependencia de Retrofit para utilizar la librería Gson para la serialización a JSON y deserialización a objetos
    implementation(libs.androidx.material.icons.extended) //Dependencia para poder utilizar todos los iconos de M3
    implementation(libs.kotlinx.coroutines.core) //Dependencia que proporciona las funciones principales para trabajar con corrutinas
    implementation(libs.kotlinx.coroutines.android) //Dependencia para optimizar las corrutinas con el Main Dispatcher en Android
    testImplementation (libs.kotlinx.coroutines.test) //Dependencia para ejecutar las corrutinas en los tests unitarios sin bloquear el hilo principal
    testImplementation (libs.androidx.core.testing) //Dependencia para realizar pruebas unitarias con ViewModel y LiveData
    implementation(libs.androidx.datastore.preferences) //Dependencia para poder utilizar la librería DataStore Preferences de Jetpack
    implementation(libs.firebase.auth) //Dependencia para utilizar la librería de Firebase Authentication para la generación de tokens de usuarios
    implementation(libs.androidx.room.runtime) //Dependencia para poder utilizar la librería Room para facilitar las operaciones CRUD en una BD SQLite
    ksp(libs.androidx.room.compiler) //Dependencia para poder utilizar la herramienta KSP (Kotlin Symbol Processing) para las anotaciones de Room
    implementation(libs.androidx.room.ktx) //Dependencia opcional que incluye funciones de extensión y soporte para corrutinas en DAO, simplificando el uso de Room
    testImplementation(libs.mockk) //Dependencia para poder utilizar la librería de testing MockK
    testImplementation(libs.junit.jupiter) //Dependencia para poder utilizar la librería JUnit 5 para los unit tests
    implementation (libs.hilt.android) //Dependencia para poder utilizar la librería Dagger Hilt para DI
    ksp (libs.hilt.compiler) //Dependencia para poder utilizar la herramient KSP (Kotlin Symbol Processing) para DI con Dagger Hilt
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
