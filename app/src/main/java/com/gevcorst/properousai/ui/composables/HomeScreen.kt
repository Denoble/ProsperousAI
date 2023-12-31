package com.gevcorst.properousai.ui.composables


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.gevcorst.properousai.ui.composables.custom.BottomNavigationBar
import com.gevcorst.properousai.ui.composables.custom.CustomAppBar
import com.gevcorst.properousai.ui.theme.ProperousAITheme



@Composable
fun HomeScreen(screenName:String,icon: ImageVector,appState:AppState
) {
    ProperousAITheme {
        Surface(color = MaterialTheme.colorScheme.secondary) {

        }
    }
}

