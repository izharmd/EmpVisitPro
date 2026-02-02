package com.jslps.empvisist.presentation.viewmodel

import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.domain.usecase.GetUserDetailsUseCase
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {
    private lateinit var viewModel: UserViewModel
    private lateinit var useCase: GetUserDetailsUseCase

    private val testDispatcher = StandardTestDispatcher()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk()
        viewModel = UserViewModel(useCase,testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearMocks(useCase)
    }

    @Test
    fun `getUserDetailsList updates state flow with user data`() = runTest {
        // Given
        val loginData = tblLogin(
            userId = 1,
            username = "Izhar",
            mobilenumber = "9999999999"
        )

        coEvery {
            useCase.getUserDetails()
        } returns flowOf(loginData)

        // When
        viewModel.getUserDetailsList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val result = viewModel.arrayUseList.value
        assertEquals("Izhar", result.username)
        assertEquals(1, result.userId)

        coVerify(exactly = 1) {
            useCase.getUserDetails()
        }
    }

    @Test
    fun `getUserDetailsList with empty flow keeps default state`() = runTest {
        coEvery { useCase.getUserDetails() } returns emptyFlow()

        viewModel.getUserDetailsList()
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.arrayUseList.value

        assertEquals(0, result.userId)
        assertEquals("", result.username)
    }



    @Test
    fun `getUserDetailsList handles exception without crashing`() = runTest {
        coEvery {
            useCase.getUserDetails()
        } returns flow {
            throw RuntimeException("API failure")
        }

        viewModel.getUserDetailsList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Default state should remain
        val result = viewModel.arrayUseList.value
        assertEquals(0, result.userId)
    }

    @Test
    fun `getUserDetailsList calls use case only once`() = runTest {
        coEvery {
            useCase.getUserDetails()
        } returns flowOf(tblLogin(1, "Izhar", "999"))

        viewModel.getUserDetailsList()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) {
            useCase.getUserDetails()
        }
    }

    @Test
    fun `calling getUserDetailsList twice collects twice`() = runTest {
        coEvery {
            useCase.getUserDetails()
        } returns flowOf(tblLogin(1, "Izhar", "999"))

        viewModel.getUserDetailsList()
        viewModel.getUserDetailsList()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 2) {
            useCase.getUserDetails()
        }
    }


}