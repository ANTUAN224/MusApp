package com.project.musapp.feature.home.user.register.domain.useCase

import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import com.project.musapp.feature.user.auth.registration.domain.usecase.InsertUserUseCase
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class) //Me ahorro el inicializar los objetos anotados con @MockK o @RelaxedMockK con 'MockKAnnotations.init(this)'.
class UserRegisterUseCaseTest {
    @MockK
    private lateinit var userRegistrationRepository: UserRegistrationRepository

    private lateinit var insertUserUseCase: InsertUserUseCase

    @BeforeAll
    fun setUp() {
        insertUserUseCase = InsertUserUseCase(userRegistrationRepository)
    }

    @Test
    fun `when the repository invokes 'insertUser' function, then returns true`() = runTest {
//        //Given
//        coEvery { userRegistrationRepository.insertUser(any()) } returns true
//
//        //When
//        val response = insertUserUseCase(mockk< UserRegistrationModel>())
//
//        //Then
//        coVerify(exactly = 1) { userRegistrationRepository.insertUser(any()) }
//        assertTrue(response)
    }
}