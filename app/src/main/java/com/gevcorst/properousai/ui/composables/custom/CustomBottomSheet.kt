package com.gevcorst.properousai.ui.composables.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import com.gevcorst.properousai.ui.theme.MilkyWhite
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gevcorst.properousai.R.drawable as AppIcons
import com.gevcorst.properousai.R.string as AppText

var isBottomSheetVisible = mutableStateOf(false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    title: String, background: Color, modifier: Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = { false },
    onProceed: () -> Unit,
){
    val scope = rememberCoroutineScope()
    var showBottomSheet = remember { isBottomSheetVisible }
    if (isBottomSheetVisible.value) {

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = RectangleShape,
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = .5f),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {

            // Implement the custom layout here ...
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                colors = CardDefaults.cardColors(containerColor = MilkyWhite)
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()){
                    val(titleText,closeImage,button) = createRefs()
                    CustomText(text = title, modifier = Modifier.constrainAs(titleText){
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    } , onClickAction = { })
                    CustomImage(url =
                    LocalContext.current.getDrawable ( AppIcons.baseline_close_24).toString(),
                        modifier = Modifier
                            .constrainAs(closeImage) {
                                top.linkTo(titleText.top)
                                start.linkTo(titleText.end, margin = 8.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            }
                            .clickable {
                                isBottomSheetVisible.value = false
                            }, )
                    BasicTextButton(
                        text = AppText.proceed,
                        modifier = Modifier.constrainAs(button){
                            top.linkTo(titleText.bottom,margin =16.dp)
                            end.linkTo(closeImage.end)
                        }) {
                        //Run Action
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onProceed:() -> Unit,
) {

    if (isBottomSheetVisible) {

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = RectangleShape,
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = .5f),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {

        }

    }

}