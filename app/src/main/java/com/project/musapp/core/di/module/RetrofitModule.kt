package com.project.musapp.core.di.module

import com.project.musapp.BuildConfig
import com.project.musapp.feature.artwork.data.source.remote.api.ArtworkApiService
import com.project.musapp.feature.auth.data.source.remote.apiservice.UserAuthApiService
import com.project.musapp.feature.user.data.source.remote.api.UserApiService
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
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideUserAuthenticationApiService(retrofit: Retrofit): UserAuthApiService =
        retrofit.create(UserAuthApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideArtworkApiService(retrofit: Retrofit): ArtworkApiService =
        retrofit.create(ArtworkApiService::class.java)
}