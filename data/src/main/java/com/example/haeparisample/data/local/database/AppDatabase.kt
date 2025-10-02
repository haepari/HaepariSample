package com.example.haeparisample.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.haeparisample.data.local.database.dao.SampleDao
import com.example.haeparisample.data.local.database.entity.SampleEntity

@Database(
    entities = [SampleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sampleDao(): SampleDao

    companion object {
        const val DATABASE_NAME = "haepari_database"
    }
}
