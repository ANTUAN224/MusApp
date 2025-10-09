package com.project.musapp.feature.home.user.register.domain.useCase

import com.project.musapp.feature.user.register.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import com.project.musapp.feature.user.register.domain.usecase.RegisterUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class) //Me ahorro el inicializar los objetos anotados con @MockK o @RelaxedMockK con 'MockKAnnotations.init(this)'.
class UserRegisterUseCaseTest {
    @MockK
    private lateinit var userRegisterRepository: UserRegisterRepository

    private lateinit var registerUserUseCase: RegisterUserUseCase

    @BeforeAll
    fun setUp() {
        registerUserUseCase = RegisterUserUseCase(userRegisterRepository)
    }

    @Test
    fun `when the repository invokes 'insertUser' function, then returns true`() = runTest {
        //Given
        coEvery { userRegisterRepository.insertUser(any()) } returns true

        //When
        val response = registerUserUseCase(mockk< UserRegistrationModel>())

        //Then
        coVerify(exactly = 1) { userRegisterRepository.insertUser(any()) }
        assertTrue(response)
    }
}