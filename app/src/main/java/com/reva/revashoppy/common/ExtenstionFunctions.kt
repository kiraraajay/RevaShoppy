package com.reva.revashoppy.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val currentDate = Date(System.currentTimeMillis())
    return dateFormat.format(currentDate)
}