package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NasaEntity::class],
    version = 1,
    exportSchema = false
)

// Classe TypeConvert para converter as listas para dentro do Banco de Dados do Room
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun nasaDao(): NasaDao
}
