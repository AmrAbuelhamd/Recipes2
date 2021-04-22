package com.blogspot.soyamr.recipes2.data.util

import java.text.SimpleDateFormat
import java.util.*


fun String.toQueryString() = "%$this%"
fun Date.toString(format: String = "dd.mm.yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}