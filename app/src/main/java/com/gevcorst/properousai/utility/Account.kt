package com.gevcorst.properousai.utility

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.gevcorst.properousai.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Currency
import java.util.Locale

private val mutex = Mutex()
val canProceed = mutableStateOf(true)


suspend fun addTransaction(
    transaction: Transaction, account: MutableDoubleState,
    type: TransactionType
): Flow<String> = flow {
    mutex.lock()
    try {
        when (type) {
            TransactionType.CREDIT -> {
                credit(transaction, account).collect() {
                    if (canProceed.value)
                        account.value = it
                    else canProceed.value = true
                }
            }

            TransactionType.DEBIT -> {
                if (account.value > transaction.amount) {
                    debit(transaction, account).collect {
                        account.value = it
                    }
                } else {
                    canProceed.value = false
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

@RequiresApi(Build.VERSION_CODES.O)
suspend fun transactions(): Flow<List<Transaction>> = flow {
    populateTransactions()
    try {
        emit(transactions.value)
    } catch (e: Exception) {
        Log.d("GetTransaction", e.stackTraceToString())
        emit(emptyList())
    }
}

suspend fun balance(account: MutableDoubleState): Flow<Double> = flow {
    emit(account.value)
}

private suspend fun debit(
    transaction: Transaction,
    account: MutableDoubleState
): Flow<Double> = flow {
    val balance = account.value - transaction.amount
    transactions.value.add(transaction)
    emit(balance)
}

private suspend fun credit(
    transaction: Transaction,
    account: MutableDoubleState
): Flow<Double> = flow {
    val balance = transaction.amount + account.value
    transactions.value.add(transaction)
    emit(balance)
}

enum class TransactionType { DEBIT, CREDIT }
enum class AccountType { CHECKING, FAMILY, SAVINGS }

val currency: Currency = Currency.getInstance(Locale.getDefault())
val currencySymbol: String = currency.currencyCode
val transactions = mutableStateOf(mutableListOf<Transaction>())
val accountsDropDownList = listOf<String>(
    AccountType.CHECKING.name, AccountType.FAMILY.name,
    AccountType.SAVINGS.name
)

@RequiresApi(Build.VERSION_CODES.O)
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

@RequiresApi(Build.VERSION_CODES.O)
val date = LocalDateTime.now().format(formatter)

@RequiresApi(Build.VERSION_CODES.O)
fun populateTransactions() {
    transactions.value.add(
        Transaction(
            59000.19, date,
            "PayCHECK", TransactionType.CREDIT
        )
    )
    transactions.value.add(
        Transaction(
            130.00, date, "Juicy Crabs",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            500.00, date, "Jet AutoShop",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            40.5, date, "Chevron Gas",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            137.00, date, "GoldStar haircuts",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            1000.88, date, "Family",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            88.00, date, "UberEats",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            250.00, date, "Garden of Edens Check",
            TransactionType.CREDIT
        )
    )
    transactions.value.add(
        Transaction(
            55.15, date,
            "Circle K Gas", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            127.00,
            date, "Power Inc",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            444.02, date,
            "To Checking Account", TransactionType.CREDIT
        )
    )
    transactions.value.add(
        Transaction(
            67.00, date,
            "Juicy Grabs", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            101.00,
            date, "Landland Bar", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            55.0, date,
            "Urban Planet GYM", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            111.00,
            date, "Wild Goose Chase", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            3467.19,
            date, "Sony Check", TransactionType.CREDIT
        )
    )
    transactions.value.add(
        Transaction(
            130.00, date,
            "Doordash", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            56.00,
            date, "Uber", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            40.5,
            date, "Chevron Gas", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            137.00, date,
            "Locks haircuts", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            447.19, date,
            "Google Store", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            150.00, date, "Juicy Crabs",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            200.00, date,
            "Mehdi AutoRepairs", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            230.5, date,
            "MamaCare Restaurant", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            111.00,
            date, "A's Lounge", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            15.19,
            date, "Car wash", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            137.00, date,
            "Safeway Groceries", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            550.00,
            date, "Eye Care", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            48.45, date,
            "Chevron Gas", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            123.00, date,
            "GoldStar haircuts", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            59000.19,
            date, "To Savings", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            210.00, date, "Juicy Crabs",
            TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            650.00, date,
            "XSuits", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            120.45, date,
            "Footlocker", TransactionType.DEBIT
        )
    )
    transactions.value.add(
        Transaction(
            164.00, date, "Dental Care",
            TransactionType.DEBIT
        )
    )
}
