package com.example.haeparisample.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.haeparisample.data.local.database.entity.SampleEntity

@Dao
interface SampleDao {
    @Query("SELECT * FROM sample")
    suspend fun getAll(): List<SampleEntity>
}
