package com.project.musapp.core.di.module

import com.project.musapp.feature.user.login.data.source.remote.client.UserLoginApiClient
import com.project.musapp.feature.user.registration.data.source.remote.client.UserRegistrationApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //Proporciona instancias que no pueden ser creadas directamente con @Inject.
@InstallIn(SingletonComponent::class) //Define el alcance de las dependencias (en este caso, el scope es de Application).
object RetrofitModule {
    @Provides //Se utiliza cuando no se puede aplicar @Inject en el constructor de una clase.
    @Singleton //Indica que esta función me devuelve la misma instancia siempre (patrón de diseño Singleton).
    fun provideRetrofit(): Retrofit = Retrofit
            .Builder()
            .baseUrl("http://192.168.1.128:6000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUserRegisterApiClient(retrofit: Retrofit): UserRegistrationApiClient =
        retrofit.create(UserRegistrationApiClient::class.java)

    @Provides
    @Singleton
    fun provideUserLoginApiClient(retrofit: Retrofit): UserLoginApiClient =
        retrofit.create(UserLoginApiClient::class.java)
}