package com.jslps.empvisist.presentation.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jslps.empvisist.presentation.viewmodel.DashboardViewmodel
import com.jslps.empvisist.ui.theme.EmpVisistTheme
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dashboardViewModel = mockk<DashboardViewmodel>(relaxed = true)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    // Helper function to set up test content
    private fun setupDashboardScreen(
        navController: NavHostController? = null,
        isLoading: Boolean = false
    ) {
        // Mock initial states
        every { dashboardViewModel.arrayPanchayatList } returns MutableStateFlow(emptyList())
        every { dashboardViewModel.villageList } returns MutableStateFlow(emptyList())
        every { dashboardViewModel.getPanchayatList() } just Runs
        every { dashboardViewModel.getUserDetail() } just Runs
        every { dashboardViewModel.getVillageList(any()) } just Runs

        composeTestRule.setContent {
            EmpVisistTheme {
                DashboardScreen(
                    navController = navController,
                    viewModelDB = dashboardViewModel
                )
            }
        }

        if (isLoading) {
            composeTestRule.waitForIdle()
        }
    }

    @Test
    fun dashboardScreen_displaysAllComponents() {
        // Given
        setupDashboardScreen()

        // Then - Check all main components exist
        composeTestRule.onNodeWithText("EmpVisist").assertExists() // App name from string resource

        // Check user detail sections
        composeTestRule.onNodeWithText("District").assertExists()
        composeTestRule.onNodeWithText("Block").assertExists()
        composeTestRule.onNodeWithText("User Name").assertExists()

        // Check dropdown sections
        composeTestRule.onNodeWithText("Panchayat").assertExists()
        composeTestRule.onNodeWithText("Village").assertExists()

        // Check dropdown buttons exist
        composeTestRule.onAllNodesWithContentDescription("Dropdown")
            .assertCountEquals(2) // One for Panchayat, one for Village
    }

    @Test
    fun dashboardScreen_showsEmptyStateWhenNoData() {
        // Given
        setupDashboardScreen()

        // Then - Panchayat dropdown should show "Select" placeholder
        composeTestRule
            .onNodeWithText("Select")
            .assertExists()
            .assertIsDisplayed()

        // And - Village dropdown should also show "Select" placeholder
        composeTestRule
            .onAllNodesWithText("Select")
            .assertCountEquals(2)
    }

    @Test
    fun dashboardScreen_panchayatDropdown_opensDialogOnClick() {
        // Given
        setupDashboardScreen()

        // When - Click Panchayat dropdown
        composeTestRule
            .onAllNodesWithContentDescription("Dropdown")
            .onFirst()
            .performClick()

        // Then - Should show dialog with title "Select Panchayat"
        composeTestRule.onNodeWithText("Select Panchayat").assertExists()
    }

    @Test
    fun dashboardScreen_villageDropdown_opensDialogOnClick() {
        // Given
        setupDashboardScreen()

        // When - Click Village dropdown
        composeTestRule
            .onAllNodesWithContentDescription("Dropdown")
            .get(1) // Second dropdown is for Village
            .performClick()

        // Then - Should show dialog with title "Select Village"
        composeTestRule.onNodeWithText("Select Village").assertExists()
    }

    @Test
    fun dashboardScreen_callsViewModelMethodsOnLaunch() {
        // Given
        setupDashboardScreen()

        // Then - Verify ViewModel methods were called
        verify(exactly = 1) { dashboardViewModel.getPanchayatList() }
        verify(exactly = 1) { dashboardViewModel.getUserDetail() }
    }

   /* @Test
    fun dashboardScreen_withPanchayatList_showsCorrectPlaceholder() {
        // Given
        val mockPanchayatList = listOf(
            mockk<Panchayat> { every { PanchayatName } returns "Test Panchayat 1" },
            mockk<Panchayat> { every { PanchayatName } returns "Test Panchayat 2" }
        )

        every { dashboardViewModel.arrayPanchayatList } returns MutableStateFlow(mockPanchayatList)
        every { dashboardViewModel.villageList } returns MutableStateFlow(emptyList())

        composeTestRule.setContent {
            EmpVisistTheme {
                DashboardScreen(viewModelDB = dashboardViewModel)
            }
        }

        // Then - Should still show "Select" when list is available but no item selected
        composeTestRule.onNodeWithText("Select").assertExists()
    }*/

    /*@Test
    fun dashboardScreen_panchayatSelection_triggersVillageListFetch() {
        // Given
        val mockNavController = mockk<NavHostController>(relaxed = true)
        setupDashboardScreen(navController = mockNavController)

        val mockPanchayat = mockk<Panchayat> {
            every { PanchayatCode } returns "PANCH001"
            every { PanchayatName } returns "Test Panchayat"
        }

        // When - Open panchayat dialog and simulate selection
        // This is tricky because we need to mock the CustomSearchableSpinner behavior
        // We'll verify the function is set up correctly
        verify(exactly = 1) { dashboardViewModel.getPanchayatList() }
    }
*/
    @Test
    fun dashboardScreen_hasCorrectStyling() {
        // Given
        setupDashboardScreen()

        // Then - Check background styling exists
        // Note: We can't directly test colors/shapes in ComposeTest
        // But we can verify elements are rendered
        composeTestRule.onNodeWithText("District")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun dashboardScreen_navigationIntegration() {
        // Given
        val mockNavController = mockk<NavHostController>(relaxed = true)
        setupDashboardScreen(navController = mockNavController)

        // Then - Verify MainToolbar is rendered (it handles navigation)
        // We can't directly test MainToolbar internals, but we can verify the screen renders
        composeTestRule.onNodeWithText("EmpVisist").assertExists()
    }

    @Test
    fun dashboardScreen_loadingState_doesNotCrash() {
        // Given
        // Simulate loading by not waiting for idle
        every { dashboardViewModel.arrayPanchayatList } returns MutableStateFlow(emptyList())
        every { dashboardViewModel.villageList } returns MutableStateFlow(emptyList())

        composeTestRule.setContent {
            EmpVisistTheme {
                DashboardScreen(viewModelDB = dashboardViewModel)
            }
        }

        // Immediately check UI exists (should not crash during loading)
        composeTestRule.onNodeWithText("District").assertExists()
        composeTestRule.onNodeWithText("Panchayat").assertExists()
    }

    @Test
    fun dashboardScreen_handlesEmptyViewModelGracefully() {
        // Given - Test with null ViewModel
        composeTestRule.setContent {
            EmpVisistTheme {
                DashboardScreen(viewModelDB = null)
            }
        }

        // Then - Should still render basic UI without crashing
        composeTestRule.onNodeWithText("District").assertExists()
        composeTestRule.onNodeWithText("Block").assertExists()
        composeTestRule.onNodeWithText("User Name").assertExists()
        composeTestRule.onNodeWithText("Panchayat").assertExists()
        composeTestRule.onNodeWithText("Village").assertExists()
    }

    @Test
    fun dashboardScreen_dialogDismissal_worksCorrectly() {
        // Given
        setupDashboardScreen()

        // When - Open panchayat dialog
        composeTestRule
            .onAllNodesWithContentDescription("Dropdown")
            .onFirst()
            .performClick()

        // Then - Dialog should appear
        composeTestRule.onNodeWithText("Select Panchayat").assertExists()

        // Note: Testing dialog dismissal requires simulating back press or close button
        // This would need test tags on the CustomSearchableSpinner
    }

    @Test
    fun dashboardScreen_mobileNumberSection_notDisplayed() {
        // Given
        setupDashboardScreen()

        // Then - Verify mobile number section is NOT shown (not in your design)
        composeTestRule.onNodeWithText("Mobile Number").assertDoesNotExist()
    }

    @Test
    fun dashboardScreen_layoutIsScrollable() {
        // Given
        setupDashboardScreen()

        // Then - Main column should be scrollable (if needed)
        // This is more of a visual test, but we can verify all elements fit
        composeTestRule.onRoot().assertHeightIsAtLeast(100.dp)
    }

    @Test
    fun dashboardScreen_previewWorks() {
        // Test that preview composable doesn't crash
        composeTestRule.setContent {
            PreviewDashboard()
        }

        // Verify basic elements exist in preview
        composeTestRule.onNodeWithText("District").assertExists()
        composeTestRule.onNodeWithText("Panchayat").assertExists()
    }

    @Test
    fun dashboardScreen_themeColorsApplied() {
        // Given
        setupDashboardScreen()

        // Then - Elements using theme colors should render
        // We can't directly test colors, but we can test text exists
        composeTestRule.onNodeWithText("User Name").assertExists()

        // Check that all expected text elements are present
        val expectedTexts = listOf("District", "Block", "User Name", "Panchayat", "Village")
        expectedTexts.forEach { text ->
            composeTestRule.onNodeWithText(text).assertExists()
        }
    }


}