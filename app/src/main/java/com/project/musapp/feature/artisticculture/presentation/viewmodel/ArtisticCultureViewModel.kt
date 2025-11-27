package com.project.musapp.feature.artisticculture.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.core.network.domain.usecase.VerifyUserInternetConnectionUseCase
import com.project.musapp.feature.artisticculture.domain.usecase.GetAllCuriositiesUseCase
import com.project.musapp.feature.artisticculture.domain.usecase.GetAllTermsUseCase
import com.project.musapp.feature.artisticculture.presentation.model.CuriosityUiModel
import com.project.musapp.feature.artisticculture.presentation.model.TermUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtisticCultureViewModel @Inject constructor(
    private val verifyUserInternetConnectionUseCase: VerifyUserInternetConnectionUseCase,
    private val getAllTermsUseCase: GetAllTermsUseCase,
    private val getAllCuriositiesUseCase: GetAllCuriositiesUseCase
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _showNoInternetConnectionModal = MutableLiveData<Boolean>()
    val showNoInternetConnectionModal: LiveData<Boolean> = _showNoInternetConnectionModal

    private val _technicalGlossary = MutableLiveData<List<TermUiModel>>()
    val technicalGlossary: LiveData<List<TermUiModel>> = _technicalGlossary

    private val _selectedTerm = MutableLiveData<TermUiModel>()
    val selectedTerm: LiveData<TermUiModel> = _selectedTerm

    private val _showTermDefinitionModal = MutableLiveData<Boolean>()
    val showTermDefinitionModal: LiveData<Boolean> = _showTermDefinitionModal

    private val _curiosities = MutableLiveData<List<CuriosityUiModel>>()
    val curiosities: LiveData<List<CuriosityUiModel>> = _curiosities

    private val _selectedCuriosity = MutableLiveData<CuriosityUiModel>()
    val selectedCuriosity: LiveData<CuriosityUiModel> = _selectedCuriosity

    private val _showCuriosityDescriptionModal = MutableLiveData<Boolean>()
    val showCuriosityDescriptionModal: LiveData<Boolean> = _showCuriosityDescriptionModal

    fun onArtisticCultureTechnicalGlossaryScreenArrival() {
        viewModelScope.launch {
            delay(3000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _technicalGlossary.postValue(getAllTermsUseCase().getOrThrow())
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true
                }
            }

            _isLoading.value = false
        }
    }

    fun onTermClick(index: Int) {
        _selectedTerm.value = _technicalGlossary.value!![index]
        _showTermDefinitionModal.value = true
    }

    fun onTermDefinitionModalClosing() {
        _showTermDefinitionModal.value = false
    }

    fun onArtisticCultureCuriosityScreenArrival() {
        viewModelScope.launch {
            delay(3000)

            runCatching {
                verifyUserInternetConnectionUseCase().getOrThrow()

                withContext(context = Dispatchers.IO) {
                    _curiosities.postValue(getAllCuriositiesUseCase().getOrThrow())
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is NetworkException.NoInternetConnectionException ->
                        _showNoInternetConnectionModal.value = true

                }
            }

            _isLoading.value = false
        }
    }

    fun onCuriosityClick(index: Int) {
        _selectedCuriosity.value = _curiosities.value!![index]
        _showCuriosityDescriptionModal.value = true
    }

    fun onCuriosityDescriptionModalClosing() {
        _showCuriosityDescriptionModal.value = false
    }
}