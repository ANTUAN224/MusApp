package com.project.musapp.feature.artwork.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.core.network.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.feature.artwork.domain.usecase.GetArtworkUseCase
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val getArtworkUseCase: GetArtworkUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showNoInternetConnectionModal = MutableLiveData<Boolean>()
    val showNoInternetConnectionModal: LiveData<Boolean> = _showNoInternetConnectionModal

    private val _hasArtworkBeenMarkedAsFavorite = MutableLiveData<Boolean>()
    val hasArtworkBeenMarkedAsFavorite: LiveData<Boolean> = _hasArtworkBeenMarkedAsFavorite

    private val _artwork = MutableLiveData<ArtworkUiModel>()
    val artwork: LiveData<ArtworkUiModel> = _artwork

    fun onArtworkFavoriteIconClick(markedState: Boolean) {
        _hasArtworkBeenMarkedAsFavorite.value = markedState
    }

    fun onArtworkInformationScreenArrival(artworkId: Long) {
        viewModelScope.launch {
            delay(2000)

            runCatching {
                Log.d("EJECUCIÓN", "Verificación la conexión a Internet...")
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    Log.d("EJECUCIÓN", "Obteniendo la información del cuadro...")
                    _artwork.postValue(getArtworkUseCase(artworkId = artworkId).getOrThrow())
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }

                Log.d("EJECUCIÓN", "Error durante la ejecución -> $throwable")
            }

            _isLoading.value = false
        }
    }
}