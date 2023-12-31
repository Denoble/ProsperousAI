package com.gevcorst.properousai.ui.composables


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gevcorst.properousai.ui.composables.custom.BasicButton
import com.gevcorst.properousai.ui.composables.custom.CustomBottomSheet
import com.gevcorst.properousai.ui.composables.custom.CustomText
import com.gevcorst.properousai.ui.composables.custom.isBottomSheetVisible
import com.gevcorst.properousai.ui.theme.MilkyWhite
import com.gevcorst.properousai.ui.theme.ProperousAITheme
import com.gevcorst.properousai.R.string as AppText
import com.gevcorst.properousai.R.drawable as APPIcons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    screenName: String, icon: ImageVector, appState: AppState
) {
    ProperousAITheme {
        Surface() {
            appState.screenName.value = screenName
            appState.screenIcon.value = icon
            if (isBottomSheetVisible.value) {
                CustomBottomSheet(title = "Send Money",
                    background = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(4.dp),
                    onDismiss = { isBottomSheetVisible.value = false }) {
                }
            }
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
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    val (depositButton, sendButton, checkingLabel,
                        checking, checkingIcon, familyIcon, familyLabel,
                        family, savingsIcon, savingsLabel,
                        savings, cards, accounts) = createRefs()
                    CustomText(text = "Accounts",
                        modifier = Modifier.constrainAs(accounts) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = { /*TODO*/ }, textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    CustomText(text = "Cards", modifier = Modifier.constrainAs(cards) {
                        top.linkTo(accounts.top)
                        end.linkTo(parent.end, margin = 16.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }, onClickAction = { }, textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    )
                    Icon(painter = painterResource(id = APPIcons.baseline_account_circle_24),
                        contentDescription = "", modifier = Modifier.constrainAs(checkingIcon) {
                            top.linkTo(accounts.top, margin = 24.dp)
                            start.linkTo(accounts.start)
                            width = Dimension.value(40.dp)
                            height = Dimension.value(40.dp)
                        })
                    CustomText(text = "Checking",
                        modifier = Modifier.constrainAs(checkingLabel) {
                            top.linkTo(checkingIcon.top, margin = 4.dp)
                            start.linkTo(checkingIcon.end, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = {})
                    CustomText(text = "VMProperty",
                        modifier = Modifier.constrainAs(checking) {
                            top.linkTo(checkingLabel.bottom, margin = 4.dp)
                            start.linkTo(checkingLabel.start)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = {})
                    Icon(painter = painterResource(id = APPIcons.baseline_family_restroom_24),
                        contentDescription = "", modifier = Modifier.constrainAs(familyIcon) {
                            top.linkTo(checking.bottom, margin = 24.dp)
                            start.linkTo(checkingIcon.start)
                            width = Dimension.value(40.dp)
                            height = Dimension.value(40.dp)
                        })
                    CustomText(text = "Family",
                        modifier = Modifier.constrainAs(familyLabel) {
                            top.linkTo(familyIcon.top, margin = 4.dp)
                            start.linkTo(familyIcon.end, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = {})
                    CustomText(text = "VMProperty",
                        modifier = Modifier.constrainAs(family) {
                            top.linkTo(familyLabel.bottom, margin = 4.dp)
                            start.linkTo(familyLabel.start)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = {})

                    Icon(painter = painterResource(id = APPIcons.baseline_attach_money_24),
                        contentDescription = "", modifier = Modifier.constrainAs(savingsIcon) {
                            top.linkTo(family.bottom, margin = 24.dp)
                            start.linkTo(familyIcon.start)
                            width = Dimension.value(40.dp)
                            height = Dimension.value(40.dp)
                        })
                    CustomText(text = "Savings",
                        modifier = Modifier.constrainAs(savingsLabel) {
                            top.linkTo(savingsIcon.top, margin = 4.dp)
                            start.linkTo(savingsIcon.end, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = {})
                    CustomText(text = "VMProperty",
                        modifier = Modifier.constrainAs(savings) {
                            top.linkTo(savingsLabel.bottom, margin = 4.dp)
                            start.linkTo(savingsLabel.start)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                        }, onClickAction = {})
                    /*BasicButton(text = AppText.sendButton,
                        modifier =Modifier.constrainAs(depositButton){
                        top.linkTo(parent.top, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.wrapContent
                    } ) {
                        isBottomSheetVisible.value = true
                    }*/

                }

            }
        }
    }
}

