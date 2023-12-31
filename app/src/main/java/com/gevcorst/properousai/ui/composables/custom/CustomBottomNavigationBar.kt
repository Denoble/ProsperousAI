package com.gevcorst.properousai.ui.composables.custom

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gevcorst.properousai.Accounts
import com.gevcorst.properousai.Transaction
import com.gevcorst.properousai.ui.composables.AppState


@Composable
fun BottomNavigationBar(appState: AppState ,
                         saveState: MutableState<Boolean> = remember {
                             mutableStateOf(false) },
                       modifier: Modifier =Modifier) {
    val screens = listOf(
        Accounts, Transaction)
    NavigationBar(modifier = modifier,
        tonalElevation = 10.dp) {
        val navBackStackEntry = appState.navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach{screen ->
            NavigationBarItem(icon = {
                Icon(imageVector = screen.icon, "")
            },
                label = { Text(text = screen.route) },
                selected = currentRoute == screen.route,
                onClick = {
                    appState.navigate(screen.route)
                },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray))
        }

    }
}