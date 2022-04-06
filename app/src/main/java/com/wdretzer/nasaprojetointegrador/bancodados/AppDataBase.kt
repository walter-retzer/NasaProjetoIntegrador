package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [NasaEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun nasaDao(): NasaDao
}
