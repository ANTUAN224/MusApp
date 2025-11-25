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
import com.project.musapp.feature.collection.domain.usecase.AddArtworkToCollectionsUseCase
import com.project.musapp.feature.collection.domain.usecase.DeleteArtworkFromCollectionsUseCase
import com.project.musapp.feature.collection.domain.usecase.GetCollectionWithoutThatArtworkUseCase
import com.project.musapp.feature.collection.domain.usecase.GetCollectionsWithThatArtworkUseCase
import com.project.musapp.feature.collection.presentation.model.CollectionBatchUiModel
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val getArtworkUseCase: GetArtworkUseCase,
    private val getCollectionsWithThatArtworkUseCase: GetCollectionsWithThatArtworkUseCase,
    private val getCollectionWithoutThatArtworkUseCase: GetCollectionWithoutThatArtworkUseCase,
    private val addArtworkToCollectionsUseCase: AddArtworkToCollectionsUseCase,
    private val deleteArtworkFromCollectionsUseCase: DeleteArtworkFromCollectionsUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showNoInternetConnectionModal = MutableLiveData<Boolean>()
    val showNoInternetConnectionModal: LiveData<Boolean> = _showNoInternetConnectionModal

    private val _collectionsWithThatArtwork = MutableLiveData<List<CollectionReadingUiModel>>()
    val collectionsWithThatArtwork: LiveData<List<CollectionReadingUiModel>> =
        _collectionsWithThatArtwork

    private val _collectionsWithoutThatArtwork = MutableLiveData<List<CollectionReadingUiModel>>()
    val collectionsWithoutThatArtwork: LiveData<List<CollectionReadingUiModel>> =
        _collectionsWithoutThatArtwork

    private val _showArtworkDeletionFromCollectionsModal = MutableLiveData<Boolean>()
    val showArtworkDeletionFromCollectionsModal: LiveData<Boolean> =
        _showArtworkDeletionFromCollectionsModal

    private val _showArtworkAdditionToCollectionsModal = MutableLiveData<Boolean>()
    val showArtworkAdditionToCollectionsModal: LiveData<Boolean> =
        _showArtworkAdditionToCollectionsModal

    private val _showNotAnyArtworksInCollectionsModal = MutableLiveData<Boolean>()
    val showNotAnyArtworksInCollectionsModal: LiveData<Boolean> =
        _showNotAnyArtworksInCollectionsModal

    private val _showNotAnyCollectionsCreatedModalInDeletionOption = MutableLiveData<Boolean>()
    val showNotAnyCollectionsCreatedModalInDeletionOption: LiveData<Boolean> =
        _showNotAnyCollectionsCreatedModalInDeletionOption

    private val _showNotAnyCollectionsCreatedModalInAdditionOption = MutableLiveData<Boolean>()
    val showNotAnyCollectionsCreatedModalInAdditionOption: LiveData<Boolean> =
        _showNotAnyCollectionsCreatedModalInAdditionOption

    private val _showArtworkInAllCollectionsModal = MutableLiveData<Boolean>()
    val showArtworkInAllCollectionsModal: LiveData<Boolean> = _showArtworkInAllCollectionsModal

    private val _checkedArtworkDeletionCollectionOptionIndexes =
        MutableLiveData<MutableList<Int>>(mutableListOf())
    val checkedArtworkDeletionCollectionOptionIndexes: LiveData<MutableList<Int>> =
        _checkedArtworkDeletionCollectionOptionIndexes

    private val _checkedArtworkAdditionCollectionOptionIndexes =
        MutableLiveData<MutableList<Int>>(mutableListOf())
    val checkedArtworkAdditionCollectionOptionIndexes: LiveData<MutableList<Int>> =
        _checkedArtworkAdditionCollectionOptionIndexes

    private val _hasArtworkBeenMarkedAsFavorite = MutableLiveData<Boolean>()
    val hasArtworkBeenMarkedAsFavorite: LiveData<Boolean> = _hasArtworkBeenMarkedAsFavorite

    private val _artwork = MutableLiveData<ArtworkUiModel>()
    val artwork: LiveData<ArtworkUiModel> = _artwork

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> = _navigateToHome

    fun onArtworkFavoriteIconClick(markedState: Boolean) {
        _hasArtworkBeenMarkedAsFavorite.value = markedState
    }

    fun onNotAnyCollectionsCreatedModalInDeletionOptionOpening() {
        _showNotAnyCollectionsCreatedModalInDeletionOption.value = true
    }

    fun onNotAnyCollectionsCreatedModalInDeletionOptionClosing() {
        _showNotAnyCollectionsCreatedModalInDeletionOption.value = false
    }

    fun onNotAnyCollectionsCreatedModalInAdditionOptionOpening() {
        _showNotAnyCollectionsCreatedModalInAdditionOption.value = true
    }

    fun onNotAnyCollectionsCreatedModalInAdditionOptionClosing() {
        _showNotAnyCollectionsCreatedModalInAdditionOption.value = false
    }

    fun onNotAnyArtworksInCollectionsModalOpening() {
        _showNotAnyArtworksInCollectionsModal.value = true
    }

    fun onNotAnyArtworksInCollectionsModalClosing() {
        _showNotAnyArtworksInCollectionsModal.value = false
    }

    fun onArtworkInAllCollectionsModalOpening() {
        _showArtworkInAllCollectionsModal.value = true
    }

    fun onArtworkInAllCollectionsModalClosing() {
        _showArtworkInAllCollectionsModal.value = false
    }

    fun onArtworkDeletionFromCollectionsModalOpening() {
        _showArtworkDeletionFromCollectionsModal.value = true
    }

    fun onArtworkDeletionFromCollectionsModalClosing() {
        _checkedArtworkDeletionCollectionOptionIndexes.value!!.clear()
        _showArtworkDeletionFromCollectionsModal.value = false
    }

    fun onArtworkDeletionCollectionOptionCheckedStateChange(index: Int, hasBeenSelected: Boolean) {
        val newCheckedArtworkDeletionCollectionOptionIndexes =
            checkedArtworkDeletionCollectionOptionIndexes.value!!.toMutableList()

        if (hasBeenSelected) {
            newCheckedArtworkDeletionCollectionOptionIndexes.add(element = index)
        } else {
            newCheckedArtworkDeletionCollectionOptionIndexes.remove(element = index)
        }

        _checkedArtworkDeletionCollectionOptionIndexes.value =
            newCheckedArtworkDeletionCollectionOptionIndexes
    }

    fun onArtworkDeletionFromCollections(artworkId: Long) {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                val collectionIds = mutableListOf<Long>()

                if (collectionsWithThatArtwork.value!!.size > 1) {
                    checkedArtworkDeletionCollectionOptionIndexes.value!!.forEach { index ->
                        collectionIds.add(
                            element = collectionsWithThatArtwork.value!!.get(index = index).id
                        )
                    }
                } else {
                    collectionIds.add(
                        element = collectionsWithThatArtwork.value!!.get(index = 0).id
                    )
                }

                withContext(context = Dispatchers.IO) {
                    deleteArtworkFromCollectionsUseCase(
                        artworkId = artworkId,
                        collectionBatchUiModel = CollectionBatchUiModel(
                            collectionIds = collectionIds
                        )
                    ).getOrThrow()
                }
            }.onSuccess {
                _navigateToHome.value = true
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }

                _navigateToHome.value = false

                Log.d("EJECUCIÓN", "Error durante la ejecución -> $throwable")
            }

            _isLoading.value = false
        }
    }

    fun onArtworkAdditionToCollectionsModalOpening() {
        _showArtworkAdditionToCollectionsModal.value = true
    }

    fun onArtworkAdditionToCollectionsModalClosing() {
        _checkedArtworkAdditionCollectionOptionIndexes.value!!.clear()
        _showArtworkAdditionToCollectionsModal.value = false
    }

    fun onArtworkAdditionCollectionOptionCheckedStateChange(index: Int, hasBeenSelected: Boolean) {
        val newCheckedArtworkAdditionCollectionOptionIndexes =
            checkedArtworkAdditionCollectionOptionIndexes.value!!.toMutableList()

        if (hasBeenSelected) {
            newCheckedArtworkAdditionCollectionOptionIndexes.add(element = index)
            Log.d("EJECUCIÓN", "Colección seleccionada")
        } else {
            newCheckedArtworkAdditionCollectionOptionIndexes.remove(element = index)
            Log.d("EJECUCIÓN", "Colección deseleccionada")
        }

        _checkedArtworkAdditionCollectionOptionIndexes.value =
            newCheckedArtworkAdditionCollectionOptionIndexes
    }

    fun onArtworkAdditionToCollections(artworkId: Long) {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                val collectionIds = mutableListOf<Long>()

                if (collectionsWithoutThatArtwork.value!!.size > 1) {
                    checkedArtworkAdditionCollectionOptionIndexes.value!!.forEach { index ->
                        collectionIds.add(
                            element = collectionsWithoutThatArtwork.value!!.get(index = index).id
                        )
                    }
                } else {
                    collectionIds.add(
                        element = collectionsWithoutThatArtwork.value!!.get(index = 0).id
                    )
                }

                withContext(context = Dispatchers.IO) {
                    addArtworkToCollectionsUseCase(
                        artworkId = artworkId,
                        collectionBatchUiModel = CollectionBatchUiModel(
                            collectionIds = collectionIds
                        )
                    ).getOrThrow()
                }
            }.onSuccess {
                _navigateToHome.value = true
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }

                _navigateToHome.value = false

                Log.d("EJECUCIÓN", "Error durante la ejecución -> $throwable")
            }

            _isLoading.value = false
        }
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

                    _collectionsWithThatArtwork.postValue(
                        getCollectionsWithThatArtworkUseCase(artworkId = artworkId).getOrThrow()
                    )

                    _collectionsWithoutThatArtwork.postValue(
                        getCollectionWithoutThatArtworkUseCase(artworkId = artworkId).getOrThrow()
                    )
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