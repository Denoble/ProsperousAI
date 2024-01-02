package com.gevcorst.properousai.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gevcorst.properousai.model.Transaction
import com.gevcorst.properousai.model.checkingAccountBalance
import com.gevcorst.properousai.model.familyAccountBalance
import com.gevcorst.properousai.model.savingAccountBalance
import com.gevcorst.properousai.utility.AccountType
import com.gevcorst.properousai.utility.TransactionType
import com.gevcorst.properousai.utility.accountsDropDownList
import com.gevcorst.properousai.utility.addTransaction
import com.gevcorst.properousai.utility.date
import com.gevcorst.properousai.utility.populateTransactions
import com.gevcorst.properousai.utility.transactions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AccountViewModel @Inject constructor() : ViewModel() {
    val fromAccount = mutableStateOf("")
    val toAccount = mutableStateOf("")
    val amountInputState = mutableStateOf("")
    val dropDownList = accountsDropDownList
    val transactionList: MutableList<Transaction> = transactions.value
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
init {
    populateTransactions()
}
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
        Log.i("Transaction", transactions.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun transfer(
        from: String,
        to: String,
        amount: Double
    ) {
        if ((from.isNullOrBlank().not() && to.isNullOrBlank().not())
            && (from != to)) {
            Log.i("Deposit", "transfer-> $from to -> $to")
            processFromAccount(from, amount)
            processToAccount(to, amount)

        } else {
            //alertDialog
        }
        Log.i("Transaction", transactions.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun processToAccount(to: String, amount: Double) {
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
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun processFromAccount(from: String, amount: Double) {
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
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun processTransaction(
        amount: Double,
        des: String, transactionType: TransactionType,
        account: MutableDoubleState
    ) {
        val transaction = Transaction(amount, date, des,transactionType)
        viewModelScope.launch {
            addTransaction(transaction, account, transactionType).collect{

            }
        }
    }
}