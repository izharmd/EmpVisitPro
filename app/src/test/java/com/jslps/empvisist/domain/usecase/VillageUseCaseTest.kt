package com.jslps.empvisist.domain.usecase

import com.jslps.empvisist.data.local.entites.tblVillageList
import com.jslps.empvisist.domain.repository.VillageRepository
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class VillageUseCaseTest {
    private lateinit var useCase: VillageUseCase
    private var repository: VillageRepository = mockk()


    @Before
    fun setUp() {
        useCase = VillageUseCase(repository)

    }

    @After
    fun tearDown() {
       // clearMocks(repository)
        unmockkAll()
    }

    @Test
    fun `getVillageList returns village list from repository`() = runTest {
        // Given
        val clusterCode = "CL001"
        val villageList = listOf(
            tblVillageList(
                ClfCode = "",
                PanchayatCode = "",
                LgdVillage = "",
                VillageId = "",
                VillageCode = "V001",
                VillageName = "Village 1"
            ),
            tblVillageList(
                ClfCode = "",
                PanchayatCode = "",
                LgdVillage = "",
                VillageId = "",
                VillageCode = "V002",
                VillageName = "Village 2"
            ),
        )

        coEvery {
            repository.getVillageList1(clusterCode)
        } returns flowOf(villageList)

        // When
        val result = useCase.getVillageList(clusterCode).first()

        // Then
        assertEquals(2, result.size)
        assertEquals("Village 1", result[0].VillageName)

        coVerify(exactly = 1) {
            repository.getVillageList1(clusterCode)
        }
    }

    @Test
    fun `getVillageList returns empty list`() = runTest {
        val clusterCode = "CL002"

        coEvery {
            repository.getVillageList1(clusterCode)
        } returns flowOf(emptyList())

        val result = useCase.getVillageList(clusterCode).first()

        assertTrue(result.isEmpty())
    }

}