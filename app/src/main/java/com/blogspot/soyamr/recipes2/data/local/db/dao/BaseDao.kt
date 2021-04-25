package com.blogspot.soyamr.recipes2.data.local.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<T>): List<Long>

    @Delete
    suspend fun delete(item: T): Int

    @Delete
    suspend fun deleteAll(item: List<T>)

    @Update
    suspend fun update(item: T): Int
}