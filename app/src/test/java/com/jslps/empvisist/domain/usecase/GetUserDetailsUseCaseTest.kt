package com.jslps.empvisist.domain.usecase

import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.domain.repository.GetUserDetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetUserDetailsUseCaseTest {

    lateinit var useCase: GetUserDetailsUseCase
    private var repository: GetUserDetailsRepository = mockk()


    @Before
    fun setUp() {
        useCase = GetUserDetailsUseCase(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getUserDetails returns user details from repository`() = runTest {
        // Given
        val loginData = tblLogin(
            userId = 0,
            username = "Izhar",
            mobilenumber = "9999999999"
        )

        coEvery {
            repository.getUserDetails()
        } returns flowOf(loginData)

        // When
        val result = useCase.getUserDetails().first()

        // Then
        assertEquals(0, result.userId)
        assertEquals("Izhar", result.username)

        coVerify(exactly = 1) {
            repository.getUserDetails()
        }
    }

    @Test
    fun `getUserDetails returns empty flow`() = runTest {

        coEvery {
            repository.getUserDetails()
        } returns emptyFlow()

        val result = useCase.getUserDetails().firstOrNull()

        assertNull(result)
    }

}