package com.jslps.empvisist.presentation.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jslps.empvisist.presentation.screen.DashboardScreen
import com.jslps.empvisist.presentation.screen.LoginScreen
import com.jslps.empvisist.presentation.viewmodel.DashboardViewmodel
import com.jslps.empvisist.presentation.viewmodel.LoginViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = "root"
    ) {
        navigation(startDestination = Screens.LoginScreen.route, route = "root") {

            composable(Screens.LoginScreen.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("root")
                }
                val loginViewModel: LoginViewModel = hiltViewModel(parentEntry)
                Log.d("TAG", "View Model: LoginScreen " + loginViewModel)
                LoginScreen(navController,loginViewModel)
            }

            composable(Screens.DashboardScreen.route) {
                val sharedViewModel: DashboardViewmodel =
                    hiltViewModel(navController.getBackStackEntry("root"))
                DashboardScreen(navController, sharedViewModel)
            }

          /*  composable(Screens.SectionScreen.route) {
                val sharedViewModel: MainViewModel =
                    hiltViewModel(navController.getBackStackEntry("root"))
                SectionScreen(navController, sharedViewModel)
            }

            composable(Screens.HomeScreen.route) {
                val sharedViewModel: MainViewModel =
                    hiltViewModel(navController.getBackStackEntry("root"))
                Log.d("TAG", "View Model: HomeScreen " + sharedViewModel)
                HomeScreen(navController, sharedViewModel)
            }

            composable(Screens.SettingsScreen.route) {
                val sharedViewModel: MainViewModel =
                    hiltViewModel(navController.getBackStackEntry("root"))
                Log.d("TAG", "View Model: SettingsScreen" + sharedViewModel)
                SettingsScreen(navController, sharedViewModel)
            }*/
        }
    }
}
