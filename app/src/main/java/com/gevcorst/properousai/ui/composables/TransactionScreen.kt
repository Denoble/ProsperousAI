package com.gevcorst.properousai.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.gevcorst.properousai.ui.theme.MilkyWhite

@Composable
fun TransactionScreen(screenName:String,
                      icon:ImageVector,
                      appState: AppState){
    Surface {
        appState.screenName.value = screenName
        appState.screenIcon.value = icon
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
                .padding(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
            colors = CardDefaults.cardColors(containerColor = MilkyWhite)
        ){}
    }
}