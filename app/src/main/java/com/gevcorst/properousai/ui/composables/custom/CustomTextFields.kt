package com.gevcorst.properousai.ui.composables.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    label: String,
    value:String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeHolderText: String,
    modifier: Modifier,
    keyboardType: KeyboardType,
    onTextChange:(text:String)->Unit

) {
    TextField(
        value = value,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolderText) },
        visualTransformation = visualTransformation,
        onValueChange = {
            onTextChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomText(
    text: String, modifier: Modifier,
    textStyle: TextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal, fontSize = 13.sp,
    ),
    onClickAction: () -> Unit,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text, modifier = modifier.clickable {
            onClickAction.invoke()
        }, style = textStyle, textAlign = textAlign
    )
}


