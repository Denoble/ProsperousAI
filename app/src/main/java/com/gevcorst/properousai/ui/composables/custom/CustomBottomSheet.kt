package com.gevcorst.properousai.ui.composables.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gevcorst.properousai.ui.theme.MilkyWhite
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.gevcorst.properousai.viewModel.AccountViewModel
import com.gevcorst.properousai.R.drawable as AppIcons
import com.gevcorst.properousai.R.string as AppText

var isBottomSheetVisible = mutableStateOf(false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    title: String, background: Color,
    modifier: Modifier,
    viewModel: AccountViewModel=hiltViewModel(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit = { false },
){
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
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
                colors = CardDefaults.cardColors(containerColor = MilkyWhite)
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()){
                    val(titleText,closeImage,fromDropDown,
                        amountText,
                        button) = createRefs()
                    CustomText(text = title, modifier = Modifier
                        .constrainAs(titleText) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                            baseline.linkTo(closeImage.baseline)
                        }
                        .padding(16.dp) , onClickAction = { }, textStyle = TextStyle(textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold, fontSize = 16.sp))
                    Icon(
                        painter = painterResource(id = AppIcons.baseline_close_24),
                        contentDescription = "",
                        modifier = Modifier
                            .constrainAs(closeImage) {
                                top.linkTo(titleText.top)
                                end.linkTo(parent.end, margin = 8.dp)
                                width = Dimension.value(16.dp)
                                height = Dimension.wrapContent
                            }
                            .clickable {
                                isBottomSheetVisible.value = false
                            }
                            .padding(top = 16.dp),tint= Color.Red
                    )
                    Column(modifier  = Modifier.constrainAs(fromDropDown){
                        top.linkTo(closeImage.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height= Dimension.wrapContent},
                        verticalArrangement = Arrangement.Center) {
                        CustomDropdownMenu(options = listOf(
                            "Checking","Savings","Family") ,name ="From",
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            onActionClick = {viewModel.fromAccount.value = it} )
                        CustomDropdownMenu(options = listOf(
                            "Checking","Savings","Family") ,name ="To",
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            onActionClick = {viewModel.fromAccount.value = it} )
                        CustomOutlinedTextField(
                            label = stringResource(id = AppText.enter_amount),
                            value = "",
                            placeHolderText = stringResource(id = AppText.enter_amount),
                            keyboardType = KeyboardType.Decimal,
                            onTextChange = {

                            },
                            modifier = modifier.fillMaxWidth()
                                .wrapContentHeight().padding(20.dp))
                    }

                    BasicButton(text = AppText.proceed , modifier = Modifier.constrainAs(button) {
                        top.linkTo(fromDropDown.bottom)
                        end.linkTo(fromDropDown.end, margin = 20.dp)
                        width = Dimension.fillToConstraints
                        height= Dimension.wrapContent
                    }) {

                    }

                }

            }

        }
    }
}
