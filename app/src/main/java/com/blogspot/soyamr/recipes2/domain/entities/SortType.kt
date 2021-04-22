package com.blogspot.soyamr.recipes2.domain.entities;

enum class SortType(val key: String) {
    ByName("name;"),
    ByDate("date;"),
    Nothing(";");

    companion object {
        fun getByKey(value: String): SortType = values().find { it.key == value } ?: Nothing
    }
}