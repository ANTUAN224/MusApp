package com.project.musapp.core.di.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //Actúa como contenedor raíz para inyectar dependencias en toda la app.
class MusApp : Application()