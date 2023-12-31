package com.gevcorst.properousai

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

interface NavDestinations {
    val label:String
    val icon: ImageVector
    val route: String
}
object Accounts :NavDestinations{
    override val label: String = "Accounts"

    override val icon: ImageVector = Icons.Filled.AccountCircle
    override val route:String =  "Accounts"
}
object Transaction :NavDestinations{
    override val label: String = "Transaction"
    override val icon: ImageVector = Icons.Default.Info
    override val route:String =  "Transaction"
}
