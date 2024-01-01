package com.gevcorst.properousai.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gevcorst.properousai.model.Transaction
import com.gevcorst.properousai.model.checkingAccountBalance
import com.gevcorst.properousai.model.familyAccountBalance
import com.gevcorst.properousai.model.savingAccountBalance
import com.gevcorst.properousai.utility.AccountType
import com.gevcorst.properousai.utility.TransactionType
import com.gevcorst.properousai.utility.addTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import javax.annotation.meta.When
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() : ViewModel() {
    val fromAccount = mutableStateOf("")
    val toAccount = mutableStateOf("")
    val amountInputState = mutableStateOf("")
    var checkingAccountsUIState = checkingAccountBalance
        private set
    var familyAccountsUIState = familyAccountBalance
        private set
    var savingsAccountsUIState = savingAccountBalance
    private val checking
        get() = checkingAccountsUIState.value
    private val savings
        get() = savingsAccountsUIState.value
    private val family
        get() = familyAccountsUIState.value

    fun onAmountInputChange(newAmount: String) {
        amountInputState.value = newAmount
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deposit(to: String, amount: Double, type: TransactionType = TransactionType.CREDIT) {

        when (to) {
            AccountType.SAVINGS.name -> {
                val account = savingsAccountsUIState
                processTransaction(
                    amount, "Income", type,
                    account
                )
            }

            AccountType.CHECKING.name -> {
                val account = checkingAccountsUIState
                processTransaction(
                    amount, "Income", type,
                    account
                )
            }

            AccountType.FAMILY.name -> {
                val account = familyAccountsUIState
                processTransaction(
                    amount, "Income", type,
                    account
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun transfer(
        from: String,
        to: String,
        amount: Double
    ) {
        if (from != to) {
            when (from) {
                AccountType.SAVINGS.name -> {
                    val account = savingsAccountsUIState
                    processTransaction(
                        amount, "Expenses", TransactionType.DEBIT,
                        account
                    )
                }

                AccountType.CHECKING.name -> {
                    val account = checkingAccountsUIState
                    processTransaction(
                        amount, "Expenses", TransactionType.DEBIT,
                        account
                    )
                }

                AccountType.FAMILY.name -> {
                    val account = familyAccountsUIState
                    processTransaction(
                        amount, "Expenses", TransactionType.DEBIT,
                        account
                    )
                }
            }
            when (to) {
                AccountType.SAVINGS.name -> {
                    val account = savingsAccountsUIState
                    processTransaction(
                        amount, "Income", TransactionType.CREDIT,
                        account
                    )
                }

                AccountType.CHECKING.name -> {
                    val account = checkingAccountsUIState
                    processTransaction(
                        amount, "Income", TransactionType.CREDIT,
                        account
                    )
                }

                AccountType.FAMILY.name -> {
                    val account = familyAccountsUIState
                    processTransaction(
                        amount, "Income", TransactionType.CREDIT,
                        account
                    )
                }
            }
        } else {
            //alertDialog
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun processTransaction(
        amount: Double,
        des: String, transactionType: TransactionType,
        account: MutableDoubleState
    ) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val date = LocalDateTime.now().format(formatter)
        val transaction = Transaction(amount, date, des)
        viewModelScope.launch {
            Log.i("Deposit", "Amount-> $amount $date")
            addTransaction(transaction, account, transactionType).collect{

            }
        }
    }
}