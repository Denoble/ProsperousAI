package com.gevcorst.properousai.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor():ViewModel() {
    val fromAccount = mutableStateOf("")
    val toAccount = mutableStateOf("")
    val activeBankAccount = mutableStateOf(listOf<String>())

}