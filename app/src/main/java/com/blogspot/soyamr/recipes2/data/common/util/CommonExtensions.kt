package com.blogspot.soyamr.recipes2.data.common.util

import java.text.SimpleDateFormat
import java.util.*


fun String.toQueryString() = "%$this%"
fun Date.toDateString(format: String = "dd.MM.yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}