package com.blogspot.soyamr.recipes2.domain;

enum class Sort(val key: String) {
    ByName("sort_by_name"),
    ByDate("sort_by_date"),
    Nothing("");

    companion object {
        fun getByKey(value: String): Sort = values().find { it.key == value } ?: Nothing
    }
}