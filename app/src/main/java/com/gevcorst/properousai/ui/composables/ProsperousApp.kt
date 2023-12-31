package com.gevcorst.properousai.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import com.gevcorst.properousai.Accounts
import com.gevcorst.properousai.ui.composables.custom.BottomNavigationBar
import com.gevcorst.properousai.ui.composables.custom.CustomAppBar
import com.gevcorst.properousai.ui.theme.ProperousAITheme

@Composable
fun ProsperousApp() {
    ProperousAITheme {
        Surface(color = MaterialTheme.colorScheme.secondary) {
            val appState = rememberAppState()
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    CustomAppBar(
                        title = appState.screenName,
                        icon = appState.screenIcon,
                        modifier = Modifier
                    ){appState.navController.navigateUp()}
                }, content = {it.toString()
                    NavHost(navController = appState.navController,
                        startDestination = Accounts.route,
                        modifier = Modifier.padding(it) ){
                        AppNavGraph(appState = appState)
                    }
                },
                bottomBar = { BottomNavigationBar(appState) })
        }
    }
}