package com.gevcorst.properousai.ui.composables

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gevcorst.properousai.Accounts
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.AppNavGraph(appState: AppState){
    composable(route = Accounts.route){
            HomeScreen(screenName = Accounts.label,
                icon = Accounts.icon,appState)
    }
}


class AppState(var screenName:MutableState<String>,
               val navController: NavHostController
               ,private val resources: Resources,
               coroutineScope: CoroutineScope
){
    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}
@Composable
fun rememberAppState(
    screenName:MutableState<String> = mutableStateOf(""),
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember( navController, resources, coroutineScope) {
    AppState(
        screenName ,
        navController,
        resources,
        coroutineScope
    )
}
@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}