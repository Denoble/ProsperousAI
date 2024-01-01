package com.gevcorst.properousai.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gevcorst.properousai.model.AccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor():ViewModel() {
    val fromAccount = mutableStateOf("")
    val amountInputState = mutableStateOf("")



    var accountsUIState = mutableStateOf(AccountState())
        private set
    private val checking
        get() = accountsUIState.value.checking
    private val savings
        get() = accountsUIState.value.saving
    private val family
        get() = accountsUIState.value.family

    fun onAmountInputChange(newAmount: String) {
        amountInputState.value = newAmount
    }
}