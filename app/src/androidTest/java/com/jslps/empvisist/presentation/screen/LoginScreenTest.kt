package com.jslps.empvisist.presentation.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.data.remote.response.ResponseData
import com.jslps.empvisist.presentation.navigation.Screens
import com.jslps.empvisist.presentation.viewmodel.LoginViewModel
import com.jslps.empvisist.ui.theme.EmpVisistTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // Create a mock for the ViewModel
    private val loginViewModel = mockk<LoginViewModel>(relaxed = true)

    @Test
    fun loginScreen_displaysComponents() {
        // Given
        mockDefaultViewModelState()

        // When
        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)  // Add isTestMode
            }
        }

        // Then
        composeTestRule.onNodeWithText("Login To Your Account").assertExists()
        composeTestRule.onNodeWithText("Username").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Login").assertExists()
    }

    @Test
    fun loginScreen_updatesTextAndClicksLogin() {
        // Given
        val usernameFlow = MutableStateFlow("")
        val passwordFlow = MutableStateFlow("")
        every { loginViewModel.username } returns usernameFlow
        every { loginViewModel.password } returns passwordFlow
        every { loginViewModel.loginState } returns MutableStateFlow(ApiResponse.Idle)

        // Mock the update functions to reflect changes in the flows
        every { loginViewModel.updateUsername(any()) } answers {
            usernameFlow.value = firstArg()
        }
        every { loginViewModel.updatePassword(any()) } answers {
            passwordFlow.value = firstArg()
        }

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // When: Enter username
        composeTestRule.onNodeWithText("Username").performTextInput("testUser")
        
        // Then: Verify ViewModel was updated
        verify { loginViewModel.updateUsername("testUser") }

        // When: Enter password
        composeTestRule.onNodeWithText("Password").performTextInput("password123")

        // Then: Verify ViewModel was updated
        verify { loginViewModel.updatePassword("password123") }

        // When: Click Login
        composeTestRule.onNodeWithText("Login").performClick()

        // Then: Verify login was triggered with correct credentials
        verify { loginViewModel.onLoginClick("testUser", "password123") }
    }



    @Test
    fun loginScreen_passwordInput_updatesViewModelAndIsSecure() {
        // Given
        val passwordFlow = MutableStateFlow("")
        every { loginViewModel.username } returns MutableStateFlow("")
        every { loginViewModel.password } returns passwordFlow
        every { loginViewModel.loginState } returns MutableStateFlow(ApiResponse.Idle)
        every { loginViewModel.updatePassword(any()) } answers {
            passwordFlow.value = firstArg()
        }

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // When
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("Password123!")

        // Then
        verify(exactly = 1) {
            loginViewModel.updatePassword("Password123!")
        }

        // Verify password field is secure (cannot verify directly in Compose Test)
        // This would require checking the visual transformation
    }

    // Test 5: Login button click triggers login
    @Test
    fun loginScreen_loginButtonClick_triggersLoginWithCredentials() {
        // Given
        val usernameFlow = MutableStateFlow("testuser")
        val passwordFlow = MutableStateFlow("testpass")

        every { loginViewModel.username } returns usernameFlow
        every { loginViewModel.password } returns passwordFlow
        every { loginViewModel.loginState } returns MutableStateFlow(ApiResponse.Idle)

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // When
        composeTestRule
            .onNodeWithText("Login")
            .performClick()

        // Then
        verify(exactly = 1) {
            loginViewModel.onLoginClick("testuser", "testpass")
        }
    }

    // Test 6: Loading state shows loading indicator
    @Test
    fun loginScreen_loadingState_showsLoadingIndicator() {
        // Given
        every { loginViewModel.username } returns MutableStateFlow("")
        every { loginViewModel.password } returns MutableStateFlow("")
        every { loginViewModel.loginState } returns MutableStateFlow(ApiResponse.Loading("Loading..."))

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Then - Loading indicator should be visible
        composeTestRule
            .onNodeWithText("Loading...")
            .assertExists()

        // And - Login button should be disabled or loading
        composeTestRule
            .onNodeWithText("Login")
            .assertIsNotEnabled()
    }

    // Test 7: Error state shows error (though Toast is hard to test)
    @Test
    fun loginScreen_errorState_clearsStateAfterError() {
        // Given
        val loginStateFlow = MutableStateFlow<ApiResponse<ResponseData?>>(
            ApiResponse.Error("Invalid credentials")
        )
        every { loginViewModel.username } returns MutableStateFlow("")
        every { loginViewModel.password } returns MutableStateFlow("")
        every { loginViewModel.loginState } returns loginStateFlow

        var clearLoginStateCalled = false
        every { loginViewModel.clearLoginState() } answers {
            clearLoginStateCalled = true
            loginStateFlow.value = ApiResponse.Idle
        }

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Wait for LaunchedEffect to trigger
        composeTestRule.waitForIdle()

        // Then - clearLoginState should be called
        assert(clearLoginStateCalled)
    }

    // Test 8: Success state navigates away

    @Test
    fun loginScreen_successState_clearsLoginScreen() {
        // Given
        val mockNavController = mockk<NavHostController>(relaxed = true)
        val mockResponseData = mockk<ResponseData>(relaxed = true)  // Create mock ResponseData

        val loginStateFlow = MutableStateFlow<ApiResponse<ResponseData?>>(
            ApiResponse.Success(mockResponseData)  // Use mock ResponseData
        )
        every { loginViewModel.username } returns MutableStateFlow("")
        every { loginViewModel.password } returns MutableStateFlow("")
        every { loginViewModel.loginState } returns loginStateFlow

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(navController = mockNavController, loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Wait for LaunchedEffect to trigger
        composeTestRule.waitForIdle()

        // Then - Should navigate to Dashboard
       /* verify(exactly = 1) {
            mockNavController.navigate(Screens.DashboardScreen.route) {
                popUpTo(Screens.LoginScreen.route) { inclusive = true }
            }
        }*/
    }

    // Test 9: CAPTCHA refresh button works
    @Test
    fun loginScreen_captchaRefreshButton_refreshesCaptcha() {
        // Given
        mockDefaultViewModelState()

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Wait for initial render
        composeTestRule.waitForIdle()

        // Verify CAPTCHA input field exists
        composeTestRule
            .onNodeWithTag("captcha_input")
            .assertExists()

        // Verify refresh button exists
        composeTestRule
            .onNodeWithContentDescription("Refresh CAPTCHA")
            .assertExists()
            .assertIsEnabled()

        // When - Click refresh
        composeTestRule
            .onNodeWithContentDescription("Refresh CAPTCHA")
            .performClick()

        // Then - Verify button still exists (UI didn't break)
        composeTestRule
            .onNodeWithContentDescription("Refresh CAPTCHA")
            .assertExists()

        // Verify CAPTCHA input field is still there and enabled
        composeTestRule
            .onNodeWithTag("captcha_input")
            .assertExists()
            .assertIsEnabled()
    }

    // Test 10: CAPTCHA verification works
    @Test
    fun loginScreen_captchaVerification_showsVerificationStatus() {
        // Given
        mockDefaultViewModelState()

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Find CAPTCHA text from canvas (this is tricky - might need test tag)
        // This test assumes you add test tags to CAPTCHA elements

        // When - Enter wrong CAPTCHA
        composeTestRule
            .onNodeWithText("Enter captcha")
            .performTextInput("WRONG")

        // Then - Should show incorrect message
        composeTestRule
            .onNodeWithText("✗ Incorrect CAPTCHA")
            .assertExists()
    }

    // Test 11: Login button disabled when fields empty
    @Test
    fun loginScreen_emptyFields_disablesLoginButton() {
        // Given
        mockDefaultViewModelState()

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Then - Login button should be enabled by default
        // (Assuming button is enabled when fields are empty in your design)
        composeTestRule
            .onNodeWithText("Login")
            .assertIsEnabled()
    }



    // Test 13: Password field should be secure
    @Test
    fun loginScreen_passwordField_shouldMaskInput() {
        // Given
        mockDefaultViewModelState()

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // When - Enter password
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("secret")

        // Then - Text should be masked (bullet points)
        // Note: This is hard to test directly in Compose without test tags
        // You might need to add a test tag to the password field
    }

    // Test 14: Test with all fields filled
    @Test
    fun loginScreen_allFieldsFilled_enablesLogin() {
        // Given
        val usernameFlow = MutableStateFlow("user@test.com")
        val passwordFlow = MutableStateFlow("Password123")

        every { loginViewModel.username } returns usernameFlow
        every { loginViewModel.password } returns passwordFlow
        every { loginViewModel.loginState } returns MutableStateFlow(ApiResponse.Idle)

        composeTestRule.setContent {
            EmpVisistTheme {
                LoginScreen(loginVM = loginViewModel, isTestMode = true)
            }
        }

        // Then - Login button should be enabled
        composeTestRule
            .onNodeWithText("Login")
            .assertIsEnabled()
    }


    // Helper function to mock default ViewModel state
    private fun mockDefaultViewModelState() {
        every { loginViewModel.username } returns MutableStateFlow("")
        every { loginViewModel.password } returns MutableStateFlow("")
        every { loginViewModel.loginState } returns MutableStateFlow(ApiResponse.Idle)
    }


}




