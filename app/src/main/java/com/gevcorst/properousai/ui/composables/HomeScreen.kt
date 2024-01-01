package com.gevcorst.properousai.ui.composables


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.gevcorst.properousai.ui.composables.custom.TransferCustomBottomSheet
import com.gevcorst.properousai.ui.composables.custom.CustomText
import com.gevcorst.properousai.ui.composables.custom.DepositCustomBottomSheet
import com.gevcorst.properousai.ui.composables.custom.isBottomSheetVisible
import com.gevcorst.properousai.ui.composables.custom.isDepositBottomSheetVisible
import com.gevcorst.properousai.ui.theme.MilkyWhite
import com.gevcorst.properousai.ui.theme.ProperousAITheme
import com.gevcorst.properousai.utility.currencySymbol
import com.gevcorst.properousai.viewModel.AccountViewModel
import com.gevcorst.properousai.R.drawable as APPIcons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    screenName: String,
    icon: ImageVector,
    appState: AppState,viewModel:AccountViewModel = hiltViewModel()
) {
    val uiState  =  viewModel.accountsUIState
    ProperousAITheme {
        Surface {
            appState.screenName.value = screenName
            appState.screenIcon.value = icon
            if (isBottomSheetVisible.value) {
                TransferCustomBottomSheet(title = "Move funds between Accounts",
                    background = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(4.dp),
                    viewModel = viewModel,
                    onDismiss = { viewModel.onAmountInputChange("")
                        isBottomSheetVisible.value = false
                    })
            }
            if(isDepositBottomSheetVisible.value){
                DepositCustomBottomSheet(title = "Deposit to Account",
                    background = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(4.dp),
                    viewModel = viewModel,
                    onDismiss = { viewModel.onAmountInputChange("")
                        isBottomSheetVisible.value = false
                    })
            }
            Column {
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
                        val (checkingLabel,
                            checking, checkingIcon, familyIcon, familyLabel,
                            family, savingsIcon, savingsLabel,
                            savings, cards, accounts, divider, actionText, depositIcon) = createRefs()
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
                        CustomText(text = "$currencySymbol ${ uiState.value.checking}",
                            modifier = Modifier.constrainAs(checking) {
                                top.linkTo(checkingLabel.bottom, margin = 4.dp)
                                start.linkTo(checkingLabel.start)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            }, onClickAction = {})
                        Icon(painter = painterResource(id = APPIcons.baseline_family),
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
                        CustomText(text ="$currencySymbol ${uiState.value.family}",
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
                        CustomText(text = "$currencySymbol ${uiState.value.saving}",
                            modifier = Modifier.constrainAs(savings) {
                                top.linkTo(savingsLabel.bottom, margin = 4.dp)
                                start.linkTo(savingsLabel.start)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            }, onClickAction = {})

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
                ){
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        val (transferToSavingIcon, transferToSaving, actionText,
                            checking, checkingIcon, transferTofamilyIcon, transferTofamily,
                            family, depositACheckIcon, depositACheck) = createRefs()
                        CustomText(text = "Actions",
                            modifier = Modifier.constrainAs(actionText) {
                                top.linkTo(parent.top, margin = 16.dp)
                                end.linkTo(parent.end, margin = 16.dp)
                                start.linkTo(parent.start, margin = 16.dp)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            }, onClickAction = {}, textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ), textAlign = TextAlign.Center
                        )
                        Icon(painter = painterResource(id = APPIcons.baseline_family),
                            contentDescription = "",
                            modifier = Modifier.constrainAs(transferTofamilyIcon) {
                                top.linkTo(actionText.bottom, margin = 16.dp)
                                start.linkTo(parent.start, margin = 24.dp)
                                width = Dimension.value(60.dp)
                                height = Dimension.value(40.dp)
                            })
                        CustomText(text = "Transfer to Family",
                            modifier = Modifier.constrainAs(transferTofamily) {
                                top.linkTo(transferTofamilyIcon.bottom)
                                start.linkTo(transferTofamilyIcon.start)
                                end.linkTo(transferTofamilyIcon.end)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            },
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.inversePrimary),
                            onClickAction = { isBottomSheetVisible.value = true },
                            textAlign = TextAlign.Center
                        )
                        Icon(painter = painterResource(id = APPIcons.baseline_attach_money_24),
                            contentDescription = "",
                            modifier = Modifier.constrainAs(transferToSavingIcon) {
                                top.linkTo(transferTofamilyIcon.top)
                                start.linkTo(transferTofamilyIcon.end)
                                end.linkTo(depositACheckIcon.start)
                                width = Dimension.value(60.dp)
                                height = Dimension.value(40.dp)
                            })
                        CustomText(text = "Transfer to Savings",
                            modifier = Modifier.constrainAs(transferToSaving) {
                                top.linkTo(transferToSavingIcon.bottom,)
                                end.linkTo(transferToSavingIcon.end)
                                start.linkTo(transferToSavingIcon.start,)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            },
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.inversePrimary),
                            onClickAction = { isBottomSheetVisible.value = true },
                            textAlign = TextAlign.Center
                        )

                        Icon(painter = painterResource(id = APPIcons.baseline_card_travel_24),
                            contentDescription = "",
                            modifier = Modifier.constrainAs(depositACheckIcon) {
                                top.linkTo(transferToSavingIcon.top)
                                end.linkTo(parent.end, margin = 24.dp)
                                width = Dimension.value(60.dp)
                                height = Dimension.value(40.dp)
                            })
                        CustomText(text = "Deposit a Check",
                            modifier = Modifier.constrainAs(depositACheck) {
                                top.linkTo(depositACheckIcon.bottom)
                                start.linkTo(depositACheckIcon.start)
                                end.linkTo(depositACheckIcon.end)
                                width = Dimension.fillToConstraints
                                height = Dimension.wrapContent
                            }, onClickAction = { isDepositBottomSheetVisible.value = true},
                            textStyle = TextStyle(
                                color= MaterialTheme.colorScheme.inversePrimary
                            ), textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

