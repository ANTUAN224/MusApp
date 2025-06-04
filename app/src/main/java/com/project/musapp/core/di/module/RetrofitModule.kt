package com.project.musapp.core.di.module

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
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

//    @Provides
//    @Singleton
//    fun provideRegisterUserApi(retrofit: Retrofit): RegisterUserApiService =
//        retrofit.create(RegisterUserApiService::class.java)
}