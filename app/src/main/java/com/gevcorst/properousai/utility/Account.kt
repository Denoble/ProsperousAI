package com.gevcorst.properousai.utility

import android.util.Log
import com.gevcorst.properousai.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex

interface Account {
    val name: String
    val balance: Double
    val transactions: MutableList<Transaction>
        get() = mutableListOf<Transaction>()

    suspend fun balance(): Flow<Double>
    suspend fun addTransaction(transaction: Transaction, type:TransactionType):Flow<String>
    suspend fun transactions(): Flow<List<Transaction>>
}

class AccountImpl(accountType: String,accountOwner:String="") : Account {
    private val mutex = Mutex()
    private val scope = CoroutineScope(Dispatchers.Default)
    override val name: String
        get() = name
    override var balance: Double = 0.0
        get() = balance

    override suspend fun balance(): Flow<Double>  = flow{
                mutex.lock()
                try {
                   emit( balance)
                } catch (e: Exception) {
                    Log.d("GetBalance", e.stackTraceToString())
                    emit(Double.MIN_VALUE)
                } finally {
                    mutex.unlock()
                }
    }

    override suspend fun addTransaction(transaction: Transaction,
                                        type: TransactionType): Flow<String> = flow {
        mutex.lock()
        try{
            when(type){
                TransactionType.CREDIT ->{
                   credit(transaction)
                }
                TransactionType.DEBIT ->{
                    if(balance > transaction.amount){
                       debit(transaction)
                    }
                }
            }
        }catch (e:Exception){
            val errorMessage = e.stackTraceToString()
            Log.d("GetBalance", errorMessage)
            emit(errorMessage)
        }finally {
            mutex.unlock()
        }

    }

    override suspend fun transactions(): Flow<List<Transaction>>  = flow{
        mutex.lock()
        try {
            emit(transactions)
        }catch (e:Exception){
            Log.d("GetTransaction", e.stackTraceToString())
            emit(emptyList())
        }finally {
            mutex.unlock()
        }
    }

    private suspend fun debit(transaction:Transaction):Flow<String> = flow {
        balance-=transaction.amount
        transactions.add(transaction)
        emit("${transaction.amount} was debited to your account.\n" +
                "Current balance is $balance")
    }

    private suspend fun credit(transaction: Transaction):Flow<String>  = flow{
        balance+= transaction.amount
        transactions.add(transaction)
        emit("${transaction.amount} was credited to your account.\n" +
                "Current balance is $balance")
    }
}
enum class TransactionType{DEBIT,CREDIT}