package com.jslps.empvisist.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.data.local.dao.AppDao
import com.jslps.empvisist.data.remote.response.Data
import com.jslps.empvisist.data.remote.response.ResponseData
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.repository.LoginRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {
    @MockK
    private lateinit var mockRepository: LoginRepository

    @MockK
    private lateinit var mockDao: AppDao

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        loginUseCase = LoginUseCase(mockRepository, mockDao)
    }





   /* @Test
    fun `login usecase with valid credentials returns success and saves data`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        // Mock successful response with data
        val mockClusterList = listOf(
            Cluster(1, "Cluster 1"),
            Cluster(2, "Cluster 2")
        )
        val mockVillageList = listOf(
            Village(1, "Village 1"),
            Village(2, "Village 2")
        )
        val mockUserData = listOf(
            UserData("user1", "John Doe", "district1", "block1")
        )

        val mockResponseData = ResponseData(
            data = listOf(
                Data(
                    ClusterList = mockClusterList,
                    VillageList = mockVillageList,
                    userData = mockUserData
                )
            )
        )

        val expectedResponse = ApiResponse.Success(mockResponseData)

        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // Mock DAO operations
        coEvery {
            mockDao.savetblmstClusterList(any())
        } just Runs

        coEvery {
            mockDao.savetblmstVillageList(any())
        } just Runs

        coEvery {
            mockDao.saveUserLoginData(any())
        } just Runs

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)

        // Verify repository was called with correct parameters
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO save operations were called
        coVerify(exactly = 1) {
            mockDao.savetblmstClusterList(withArg { list ->
                // Verify the list is converted correctly
                assertThat(list).isNotEmpty()
            })
        }

        coVerify(exactly = 1) {
            mockDao.savetblmstVillageList(withArg { list ->
                assertThat(list).isNotEmpty()
            })
        }

        coVerify(exactly = 1) {
            mockDao.saveUserLoginData(withArg { list ->
                assertThat(list).isNotEmpty()
            })
        }
    }*/

    @Test
    fun `login usecase when repository returns error returns same error`() = runTest {
        // Given
        val username = "testUser"
        val password = "wrongPassword"
        val errorMessage = "Invalid credentials"

        val expectedResponse = ApiResponse.Error(errorMessage)
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO operations were NOT called
        coVerify(exactly = 0) { mockDao.savetblmstClusterList(any()) }
        coVerify(exactly = 0) { mockDao.savetblmstVillageList(any()) }
        coVerify(exactly = 0) { mockDao.saveUserLoginData(any()) }
    }

    @Test
    fun `login usecase when repository returns loading returns loading`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        val expectedResponse = ApiResponse.Loading("Loading...")
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO operations were NOT called
        coVerify(exactly = 0) { mockDao.savetblmstClusterList(any()) }
        coVerify(exactly = 0) { mockDao.savetblmstVillageList(any()) }
        coVerify(exactly = 0) { mockDao.saveUserLoginData(any()) }
    }

    @Test
    fun `login usecase when repository returns idle returns idle`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        val expectedResponse = ApiResponse.Idle
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO operations were NOT called
        coVerify(exactly = 0) { mockDao.savetblmstClusterList(any()) }
        coVerify(exactly = 0) { mockDao.savetblmstVillageList(any()) }
        coVerify(exactly = 0) { mockDao.saveUserLoginData(any()) }
    }

    /*@Test
    fun `login usecase when success data has null lists handles gracefully`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        val mockResponseData = ResponseData(
            data = listOf(
                Data(
                    ClusterList = arrayListOf(),  // Null ClusterList
                    VillageList = arrayListOf(),  // Null VillageList
                    userData = arrayListOf()      // Null userData
                )
            )
        )

        val expectedResponse = ApiResponse.Success(mockResponseData)
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)

        // Verify repository was called
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO save operations were NOT called because lists are null
        coVerify(exactly = 0) { mockDao.savetblmstClusterList(any()) }
        coVerify(exactly = 0) { mockDao.savetblmstVillageList(any()) }
        coVerify(exactly = 0) { mockDao.saveUserLoginData(any()) }
    }
*/
   /* @Test
    fun `login usecase when success data has empty lists handles correctly`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        val mockResponseData = ResponseData(
            data = listOf(
                Data(
                    ClusterList = emptyList(),  // Empty ClusterList
                    VillageList = emptyList(),  // Empty VillageList
                    userData = emptyList()      // Empty userData
                )
            )
        )

        val expectedResponse = ApiResponse.Success(mockResponseData)
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)

        // Verify repository was called
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO save operations were called with empty lists
        coVerify(exactly = 1) {
            mockDao.savetblmstClusterList(withArg { list ->
                assertThat(list).isEmpty()
            })
        }

        coVerify(exactly = 1) {
            mockDao.savetblmstVillageList(withArg { list ->
                assertThat(list).isEmpty()
            })
        }

        coVerify(exactly = 1) {
            mockDao.saveUserLoginData(withArg { list ->
                assertThat(list).isEmpty()
            })
        }
    }*/

  /*  @Test
    fun `login usecase executes on IO dispatcher`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        val mockResponseData = ResponseData(
            data = listOf(
                Data(
                    ClusterList = listOf(ClusterList(1, "Test Cluster")),
                    VillageList = listOf(Village(1, "Test Village")),
                    userData = listOf(UserData("user1", "Test User", "dist", "block"))
                )
            )
        )

        val expectedResponse = ApiResponse.Success(mockResponseData)
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        coEvery { mockDao.savetblmstClusterList(any()) } just Runs
        coEvery { mockDao.savetblmstVillageList(any()) } just Runs
        coEvery { mockDao.saveUserLoginData(any()) } just Runs

        // When
        val result = loginUseCase(username, password)

        // Then - Test passes if no exception thrown (executed on IO dispatcher)
        assertThat(result).isEqualTo(expectedResponse)
    }

    @Test
    fun `login usecase when dao operations throw exception returns success but logs error`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        val mockResponseData = ResponseData(
            data = listOf(
                Data(
                    ClusterList = listOf(Cluster(1, "Test Cluster")),
                    VillageList = listOf(Village(1, "Test Village")),
                    userData = listOf(UserData("user1", "Test User", "dist", "block"))
                )
            )
        )

        val expectedResponse = ApiResponse.Success(mockResponseData)
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // Mock DAO to throw exception
        coEvery { mockDao.savetblmstClusterList(any()) } throws RuntimeException("DB Error")
        coEvery { mockDao.savetblmstVillageList(any()) } just Runs
        coEvery { mockDao.saveUserLoginData(any()) } just Runs

        // When
        val result = loginUseCase(username, password)

        // Then - Should still return success even if DAO fails
        assertThat(result).isEqualTo(expectedResponse)

        // Verify repository was called
        coVerify(exactly = 1) { mockRepository.login(username, password) }

        // Verify DAO operations were attempted
        coVerify(exactly = 1) { mockDao.savetblmstClusterList(any()) }
        coVerify(exactly = 1) { mockDao.savetblmstVillageList(any()) }
        coVerify(exactly = 1) { mockDao.saveUserLoginData(any()) }
    }
*/
    @Test
    fun `login usecase with empty username and password calls repository`() = runTest {
        // Given
        val username = ""
        val password = ""

        val expectedResponse = ApiResponse.Error("Empty credentials")
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)
        coVerify(exactly = 1) { mockRepository.login(username, password) }
    }

    @Test
    fun `login usecase with very long credentials handles correctly`() = runTest {
        // Given
        val username = "a".repeat(1000)
        val password = "b".repeat(1000)

        val expectedResponse = ApiResponse.Error("Invalid credentials")
        coEvery { mockRepository.login(username, password) } returns expectedResponse

        // When
        val result = loginUseCase(username, password)

        // Then
        assertThat(result).isEqualTo(expectedResponse)
        coVerify(exactly = 1) { mockRepository.login(username, password) }
    }

   /* @Test
    fun `login usecase preserves response data type`() = runTest {
        // Given
        val username = "testUser"
        val password = "testPassword"

        // Create a mock with generic type
        val mockResponseData: ResponseData? = mockk(relaxed = true)
        val expectedResponse = ApiResponse.Success(mockResponseData)

        coEvery { mockRepository.login(username, password) } returns expectedResponse as ApiResponse<ResponseData>

        // When
        val result = loginUseCase(username, password)

        // Then - Should preserve the ResponseData? type
        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)
        val successResult = result as ApiResponse.Success
        assertThat(successResult.data).isEqualTo(mockResponseData)
    }*/

}