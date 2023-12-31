package com.gevcorst.properousai.ui.composables


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gevcorst.properousai.ui.composables.custom.BasicButton
import com.gevcorst.properousai.ui.composables.custom.CustomBottomSheet
import com.gevcorst.properousai.ui.composables.custom.isBottomSheetVisible
import com.gevcorst.properousai.ui.theme.MilkyWhite
import com.gevcorst.properousai.ui.theme.ProperousAITheme
import com.gevcorst.properousai.R.string as AppText



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(screenName:String,icon: ImageVector,appState:AppState
) {
    ProperousAITheme {
        Surface() {
            appState.screenName.value = screenName
            if(isBottomSheetVisible.value){
                CustomBottomSheet(title = "Send Money",
                    background =MaterialTheme.colorScheme.background ,
                    modifier = Modifier.padding(4.dp),
                    onDismiss = { isBottomSheetVisible.value = false}) {

                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                colors = CardDefaults.cardColors(containerColor = MilkyWhite)
            ){
                ConstraintLayout(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)){
                    val(depositButton,sendButton) = createRefs()
                    BasicButton(text = AppText.sendButton,
                        modifier =Modifier.constrainAs(depositButton){
                        top.linkTo(parent.top, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                    } ) {
                        isBottomSheetVisible.value = true
                    }

                }

            }
        }
    }
}

