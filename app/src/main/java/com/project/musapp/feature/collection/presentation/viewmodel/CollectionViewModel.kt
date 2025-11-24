package com.project.musapp.feature.collection.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.core.network.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.feature.collection.domain.usecase.CreateCollectionUseCase
import com.project.musapp.feature.collection.domain.usecase.DeleteCollectionsUseCase
import com.project.musapp.feature.collection.domain.usecase.GetCollectionArtworksUseCase
import com.project.musapp.feature.collection.domain.usecase.RenameCollectionUseCase
import com.project.musapp.feature.collection.presentation.model.CollectionBatchUiModel
import com.project.musapp.feature.collection.presentation.model.CollectionCreationUiModel
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.feature.collection.presentation.model.CollectionRenamingUiModel
import com.project.musapp.feature.user.domain.usecase.GetUserCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val getUserCollectionsUseCase: GetUserCollectionsUseCase,
    private val createCollectionUseCase: CreateCollectionUseCase,
    private val renameCollectionUseCase: RenameCollectionUseCase,
    private val deleteCollectionsUseCase: DeleteCollectionsUseCase,
    private val getCollectionArtworksUseCase: GetCollectionArtworksUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showNoInternetConnectionModal = MutableLiveData<Boolean>()
    val showNoInternetConnectionModal: LiveData<Boolean> = _showNoInternetConnectionModal

    private val _showCollectionCreationModal = MutableLiveData<Boolean>()
    val showCollectionCreationModal: LiveData<Boolean> = _showCollectionCreationModal

    private val _showCollectionBatchDeletionModal = MutableLiveData<Boolean>()
    val showCollectionBatchDeletionModal: LiveData<Boolean> = _showCollectionBatchDeletionModal

    private val _showNotAnyCollectionsToDeleteModal = MutableLiveData<Boolean>()
    val showNotAnyCollectionsToDeleteModal: LiveData<Boolean> = _showNotAnyCollectionsToDeleteModal

    private val _showCollectionRenamingOptionModal = MutableLiveData<Boolean>()
    val showCollectionRenamingOptionModal: LiveData<Boolean> = _showCollectionRenamingOptionModal

    private val _showCollectionRenamingModal = MutableLiveData<Boolean>()
    val showCollectionRenamingModal: LiveData<Boolean> = _showCollectionRenamingModal

    private val _showNotAnyCollectionsToRenameModal = MutableLiveData<Boolean>()
    val showNotAnyCollectionsToRenameModal: LiveData<Boolean> = _showNotAnyCollectionsToRenameModal

    private val _userCollections = MutableLiveData<List<CollectionReadingUiModel>>()
    val userCollections: LiveData<List<CollectionReadingUiModel>> = _userCollections

    private val _collectionDeletionOptionCheckedIndexes =
        MutableLiveData<MutableList<Int>>(mutableListOf())
    val collectionDeletionOptionCheckedIndexes: LiveData<MutableList<Int>> =
        _collectionDeletionOptionCheckedIndexes

    private val _collectionArtworks = MutableLiveData<List<ArtworkPreviewUiModel>>()
    val collectionArtworks: LiveData<List<ArtworkPreviewUiModel>> = _collectionArtworks

    private val _collectionRenamingOptionSelectedIndex = MutableLiveData(0)
    val collectionRenamingOptionSelectedIndex: LiveData<Int> =
        _collectionRenamingOptionSelectedIndex

    private val _collectionTitle = MutableLiveData<String>()
    val collectionTitle: LiveData<String> = _collectionTitle

    private val _modifiedCollectionTitle = MutableLiveData<String>()
    val modifiedCollectionTitle: LiveData<String> = _modifiedCollectionTitle

    private val _originalCollectionTitle = MutableLiveData<String>()
    val originalCollectionTitle: LiveData<String> = _originalCollectionTitle

    private val _collectionTitleError = MutableLiveData<String>()
    val collectionTitleError: LiveData<String> = _collectionTitleError

    private val _modifiedCollectionTitleError = MutableLiveData<String>()
    val modifiedCollectionTitleError: LiveData<String> = _modifiedCollectionTitleError

    private val _isCollectionCreationModalButtonEnabled = MutableLiveData<Boolean>()
    val isCollectionCreationModalButtonEnabled: LiveData<Boolean> =
        _isCollectionCreationModalButtonEnabled

    private val _isCollectionRenamingModalButtonEnabled = MutableLiveData<Boolean>()
    val isCollectionRenamingModalButtonEnabled: LiveData<Boolean> =
        _isCollectionRenamingModalButtonEnabled

    fun onCollectionTitleChange(title: String) {
        _collectionTitle.value = title
        setCollectionTitleError(title = title)
        checkCollectionTitleError()
    }

    fun onModifiedCollectionTitleChange(title: String) {
        _modifiedCollectionTitle.value = title
        setModifiedCollectionTitleError(title = title)
        checkModifiedCollectionTitleError()
    }

    private fun setCollectionTitleError(title: String) {
        _collectionTitleError.value =
            when {
                title.isBlank() -> "El título no puede estar en blanco"
                title.length > 40 -> "El título no puede tener más de 40 caracteres."
                else -> ""
            }
    }

    private fun setModifiedCollectionTitleError(title: String) {
        _modifiedCollectionTitleError.value =
            when {
                title.isBlank() -> "El título no puede estar en blanco"
                title.length > 40 -> "El título no puede tener más de 40 caracteres."
                originalCollectionTitle.value!!.isNotEmpty()
                        && originalCollectionTitle.value == title.trim()
                    -> "El título debe ser distinto al original."

                else -> ""
            }
    }

    private fun checkCollectionTitleError() {
        _isCollectionCreationModalButtonEnabled.value =
            _collectionTitleError.value?.isEmpty() ?: false
    }

    private fun checkModifiedCollectionTitleError() {
        _isCollectionRenamingModalButtonEnabled.value =
            _modifiedCollectionTitleError.value?.isEmpty() ?: false
    }

    fun onCollectionCreationModalConfirmButtonClick() {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _userCollections.postValue(
                        createCollectionUseCase(
                            CollectionCreationUiModel(title = collectionTitle.value!!.trim())
                        ).getOrThrow()
                    )
                }
            }.onFailure { throwable ->
                when (throwable) {
                    NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }
            }

            _isLoading.value = false

            onCollectionCreationModalClosing()
        }
    }

    fun onCollectionCreationModalOpening() {
        _showCollectionCreationModal.value = true
    }

    fun onCollectionCreationModalClosing() {
        _collectionTitle.value = ""
        _showCollectionCreationModal.value = false
    }

    fun onCollectionBatchDeletionModalOpening() {
        _showCollectionBatchDeletionModal.value = true
    }

    fun onCollectionBatchDeletionModalClosing() {
        collectionDeletionOptionCheckedIndexes.value!!.clear()
        _collectionDeletionOptionCheckedIndexes.value!!.clear()

        _showCollectionBatchDeletionModal.value = false
    }

    fun onNotAnyCollectionsToDeleteModalOpening() {
        _showNotAnyCollectionsToDeleteModal.value = true
    }

    fun onNotAnyCollectionsToDeleteModalClosing() {
        _showNotAnyCollectionsToDeleteModal.value = false
    }

    fun onCollectionRenamingOptionModalOpening() {
        _showCollectionRenamingOptionModal.value = true
    }

    fun onCollectionRenamingOptionModalClosing() {
        _collectionRenamingOptionSelectedIndex.value = 0
        _showCollectionRenamingOptionModal.value = false
    }

    fun onCollectionRenamingModalOpening() {
        _originalCollectionTitle.value =
            userCollections.value!!.get(
                index = collectionRenamingOptionSelectedIndex.value!!
            ).title
        _modifiedCollectionTitle.value =
            userCollections.value!!.get(
                index = collectionRenamingOptionSelectedIndex.value!!
            ).title

        if (showCollectionRenamingOptionModal.value ?: false) {
            _showCollectionRenamingOptionModal.value = false
        }

        _showCollectionRenamingModal.value = true
    }

    fun onCollectionRenamingModalClosing() {
        _collectionTitle.value = ""
        _originalCollectionTitle.value = ""
        _modifiedCollectionTitleError.value = ""
        _collectionRenamingOptionSelectedIndex.value = 0
        _showCollectionRenamingModal.value = false
    }

    fun onNotAnyCollectionsToRenameModalOpening() {
        _showNotAnyCollectionsToRenameModal.value = true
    }

    fun onNotAnyCollectionsToRenameModalClosing() {
        _showNotAnyCollectionsToRenameModal.value = false
    }

    fun onCollectionDeletionOptionCheckedStateChange(index: Int, hasBeenSelected: Boolean) {
        val newCollectionDeletionOptionCheckedIndexes =
            collectionDeletionOptionCheckedIndexes.value!!.toMutableList()

        if (hasBeenSelected) {
            newCollectionDeletionOptionCheckedIndexes.add(element = index)
        } else {
            newCollectionDeletionOptionCheckedIndexes.remove(element = index)
        }

        _collectionDeletionOptionCheckedIndexes.value = newCollectionDeletionOptionCheckedIndexes
    }

    fun onCollectionRenamingOptionSelect(index: Int) {
        _collectionRenamingOptionSelectedIndex.value = index
    }

    fun onCollectionRenamingModalConfirmButtonClick() {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _userCollections.postValue(
                        renameCollectionUseCase(
                            collectionId = userCollections.value!!.get(index = collectionRenamingOptionSelectedIndex.value!!).id,
                            collectionRenamingUiModel = CollectionRenamingUiModel(
                                modifiedTitle = modifiedCollectionTitle.value!!
                            )
                        ).getOrThrow()
                    )
                }
            }.onFailure { throwable ->
                when (throwable) {
                    NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }
            }

            _isLoading.value = false

            onCollectionRenamingModalClosing()
        }
    }

    fun onCollectionBatchDeletion() {
        viewModelScope.launch {
            _isLoading.value = true

            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                val collectionIds = mutableListOf<Long>()

                if (userCollections.value!!.size > 1) {
                    collectionDeletionOptionCheckedIndexes.value!!.forEach { index ->
                        collectionIds.add(
                            userCollections.value!!.get(index = index).id
                        )
                    }
                } else {
                    collectionIds.add(
                        userCollections.value!!.get(index = 0).id
                    )
                }

                withContext(context = Dispatchers.IO) {
                    _userCollections.postValue(
                        deleteCollectionsUseCase(
                            collectionBatchUiModel = CollectionBatchUiModel(
                                collectionIds = collectionIds
                            )
                        ).getOrThrow()
                    )
                }
            }.onFailure { throwable ->
                when (throwable) {
                    NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }
            }

            _isLoading.value = false

            onCollectionBatchDeletionModalClosing()
        }
    }

    fun onCollectionPreviewScreenArrival() {
        viewModelScope.launch {
            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _userCollections.postValue(getUserCollectionsUseCase().getOrThrow())
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }

                Log.d("EJECUCIÓN", "Pasaron cosas -> $throwable")
            }
        }
    }

    fun onCollectionArtworkScreenArrival(collectionId: Long) {
        viewModelScope.launch {
            delay(2000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _collectionArtworks.postValue(
                        getCollectionArtworksUseCase(
                            collectionId =
                                collectionId
                        ).getOrThrow()
                    )
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }

                Log.d("EJECUCIÓN", "Pasaron cosas -> $throwable")
            }

            _isLoading.value = false
        }
    }
}