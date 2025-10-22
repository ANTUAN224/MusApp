package com.project.musapp.feature.home.user.register.domain.useCase

import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import com.project.musapp.feature.user.auth.registration.domain.usecase.CreateUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class CreateTokenUseCaseTest {
    @MockK
    private lateinit var userRegistrationRepository: UserRegistrationRepository

    private lateinit var createUserUseCase: CreateUserUseCase

    @BeforeAll
    fun setUp() {
        createUserUseCase = CreateUserUseCase(userRegistrationRepository)
    }

    @Test
    fun `when the repository call 'createToken' fun, then returns true`() = runTest {
        //Given
        coEvery { userRegistrationRepository.createUser(any<String>(), any<String>()) } returns Unit

        //When
        createUserUseCase("antoniomateos1234@gmail.com", "9087PO")

        //Then
        coVerify(exactly = 1) { userRegistrationRepository.createUser(any<String>(), any<String>()) }
    }
}