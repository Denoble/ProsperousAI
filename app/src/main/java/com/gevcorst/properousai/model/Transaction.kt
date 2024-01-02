package com.gevcorst.properousai.model

import com.gevcorst.properousai.utility.TransactionType
import java.text.DateFormat
import java.util.Date

data class Transaction(val amount:Double,val date:String,
                       val description:String,val type:TransactionType)
