package com.dicoding.picodiploma.mybank.helper

import java.text.DecimalFormat

object FunctionHelper {
    fun rupiahFormat(price: Int): String {
        val formatter = DecimalFormat("#,###")
        return "Rp " + formatter.format(price.toLong())
    }
}