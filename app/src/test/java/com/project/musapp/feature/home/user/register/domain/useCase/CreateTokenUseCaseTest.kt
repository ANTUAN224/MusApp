package com.project.musapp.feature.home.user.register.domain.useCase

import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import com.project.musapp.feature.user.register.domain.useCase.CreateTokenUseCase
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
    private lateinit var userRegisterRepository: UserRegisterRepository

    private lateinit var createTokenUseCase: CreateTokenUseCase

    @BeforeAll
    fun setUp() {
        createTokenUseCase = CreateTokenUseCase(userRegisterRepository)
    }

    @Test
    fun `when the repository call 'createToken' fun, then returns true`() = runTest {
        //Given
        coEvery { userRegisterRepository.createToken(any<String>(), any<String>()) } returns true

        //When
        val response = createTokenUseCase("antoniomateos1234@gmail.com", "9087PO")

        //Then
        coVerify(exactly = 1) { userRegisterRepository.createToken(any<String>(), any<String>()) }
        assertTrue(response)
    }
}