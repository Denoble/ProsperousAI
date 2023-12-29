package com.gevcorst.properousai.model

import java.text.DateFormat
import java.util.Date

data class Transaction(val amount:Double,val date:Date,
                       val description:String)
