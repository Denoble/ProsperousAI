package com.gevcorst.properousai.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.gevcorst.properousai.model.Transaction
import com.gevcorst.properousai.ui.composables.custom.CustomText
import com.gevcorst.properousai.ui.theme.MilkyWhite
import com.gevcorst.properousai.ui.theme.bluegrey
import com.gevcorst.properousai.utility.TransactionType
import com.gevcorst.properousai.utility.currencySymbol
import com.gevcorst.properousai.viewModel.AccountViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionScreen(
    screenName: String,
    icon: ImageVector,
    appState: AppState,
    viewModel: AccountViewModel = hiltViewModel()
) {
    Surface {
        appState.screenName.value = screenName
        appState.screenIcon.value = icon
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(3.dp, MaterialTheme.colorScheme.secondary),
            colors = CardDefaults.cardColors(containerColor = MilkyWhite)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (lazyColum) = createRefs()
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(lazyColum) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                        .background(Color.White)

                ){
                    items (viewModel.transactionList){transaction ->
                        TransactionContent( transaction,  appState) {
                           /* appState.navController.navigate(""){
                                launchSingleTop = true
                            }*/
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TransactionContent(transaction: Transaction,appState: AppState,
                       clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 48.dp)
            .clickable(onClick = { clickAction.invoke() }),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(3.dp, bluegrey),
        colors = CardDefaults.cardColors(containerColor = bluegrey)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp)
        ) {
            val (date, amount, description) = createRefs()
            val amountColor = {if(transaction.type == TransactionType.DEBIT) Color.Red
            else Color.Green}
            val amountSign ={if(transaction.type == TransactionType.DEBIT)"-"
            else "+"}
            CustomText(text = transaction.date,
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(amount.start, margin = 4.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }, onClickAction = {},
            )
            CustomText(text = "${amountSign.invoke()} $currencySymbol ${transaction.amount}",
                modifier = Modifier.constrainAs(amount) {
                    top.linkTo(date.top)
                    end.linkTo(parent.end, margin = 16.dp)
                    baseline.linkTo(date.baseline)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }, onClickAction = { }, textStyle = TextStyle(
                    color = amountColor.invoke()
                )
            )
            CustomText(text = transaction.description,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(date.bottom, margin = 16.dp)
                    start.linkTo(date.start)
                    end.linkTo(amount.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }, onClickAction = { /*TODO*/ }
            )
        }
    }

}