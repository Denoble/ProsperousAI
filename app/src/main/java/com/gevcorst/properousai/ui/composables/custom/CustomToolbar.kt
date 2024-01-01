package com.gevcorst.properousai.ui.composables.custom

import android.content.res.Resources.Theme
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.gevcorst.properousai.ui.theme.ProperousAITheme
import com.gevcorst.properousai.ui.theme.Purple80
import com.gevcorst.properousai.ui.theme.teal20
import com.gevcorst.properousai.R.string as AppText
import com.gevcorst.properousai.R.drawable as AppIcons


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int) {
    TopAppBar(title = { Text(stringResource(title)) }, colors =
    TopAppBarDefaults.largeTopAppBarColors(containerColor = toolbarColor()))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionToolbar(
    @StringRes title: Int,
    @DrawableRes endActionIcon: Int,
    modifier: Modifier,
    endAction: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            contentColorFor(backgroundColor = toolbarColor())
        ),
        actions = {
            Box(modifier) {
                IconButton(onClick = endAction) {
                    Icon(painter = painterResource(endActionIcon), contentDescription = "Action")
                }
            }
        }
    )
}

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(title:MutableState< String>,
                 icon:MutableState< ImageVector>,modifier: Modifier,
                 iconClickAction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title.value) },
        modifier = modifier,
        navigationIcon = {
            Icon(
                icon.value,
                contentDescription = title.value,
                modifier = Modifier.clickable(onClick = { iconClickAction.invoke() })
            )
        },
       colors =TopAppBarDefaults.largeTopAppBarColors(
           containerColor = teal20,

           contentColorFor(MaterialTheme.colorScheme.inversePrimary)
       )

    )
}
@Composable
fun ValidationError(msg:String,modifier: Modifier) {
    Text(text = msg, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Red,
    modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight())
}
