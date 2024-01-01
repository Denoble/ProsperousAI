package com.gevcorst.properousai.utility

import android.util.Log
import androidx.compose.runtime.MutableDoubleState
import com.gevcorst.properousai.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import java.util.Currency
import java.util.Locale

private val mutex = Mutex()


suspend fun addTransaction(
    transaction: Transaction, account: MutableDoubleState,
    type: TransactionType
): Flow<String> = flow {
    mutex.lock()
    try {
        when (type) {
            TransactionType.CREDIT -> {
                credit(transaction, account).collect() {
                    account.value = it
                }
            }

            TransactionType.DEBIT -> {
                if (account.value > transaction.amount) {
                    debit(transaction, account).collect {
                        account.value = it
                    }
                }
            }
        }
    } catch (e: Exception) {
        val errorMessage = e.stackTraceToString()
        Log.d("GetBalance", errorMessage)
        emit(errorMessage)
    } finally {
        mutex.unlock()
    }

}

suspend fun transactions(): Flow<List<Transaction>> = flow {
    mutex.lock()
    try {
        emit(transactions)
    } catch (e: Exception) {
        Log.d("GetTransaction", e.stackTraceToString())
        emit(emptyList())
    } finally {
        mutex.unlock()
    }
}

private suspend fun debit(
    transaction: Transaction,
    account: MutableDoubleState
): Flow<Double> = flow {
    val balance = account.value - transaction.amount
    transactions.add(transaction)
    emit(balance)
}

private suspend fun credit(
    transaction: Transaction,
    account: MutableDoubleState
): Flow<Double> = flow {
    val balance = transaction.amount + account.value
    transactions.add(transaction)
    emit(balance)
}

enum class TransactionType { DEBIT, CREDIT }
enum class AccountType { CHECKING, FAMILY, SAVINGS }

val currency: Currency = Currency.getInstance(Locale.getDefault())
val currencySymbol: String = currency.currencyCode
val transactions = mutableListOf<Transaction>()
val accountsDropDownList = listOf<String>(
    AccountType.CHECKING.name, AccountType.FAMILY.name,
    AccountType.SAVINGS.name
)