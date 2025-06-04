package com.project.musapp.feature.home.user.register.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.musapp.feature.user.register.domain.useCase.CreateTokenUseCase
import com.project.musapp.feature.user.register.domain.useCase.UserRegisterUseCase
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserRegisterViewModelTest {
    @MockK
    private lateinit var userRegisterUseCase: UserRegisterUseCase

    @MockK
    private lateinit var createTokenUseCase: CreateTokenUseCase

    @get:Rule
    val instantTaskExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun reset() {
        Dispatchers.resetMain()
    }

}