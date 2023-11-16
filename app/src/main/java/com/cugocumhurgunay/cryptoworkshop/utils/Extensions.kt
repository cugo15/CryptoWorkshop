package com.cugocumhurgunay.cryptoworkshop.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val date = Date(timestamp)
    return sdf.format(date)
}