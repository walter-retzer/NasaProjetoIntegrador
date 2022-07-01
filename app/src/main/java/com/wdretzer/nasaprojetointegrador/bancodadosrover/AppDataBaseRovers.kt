package com.wdretzer.nasaprojetointegrador.bancodadosrover

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wdretzer.nasaprojetointegrador.bdutil.Converters

@Database(
    entities = [RoverEntity::class],
    version = 1,
    exportSchema = false
)

// Classe TypeConvert para converter as listas para dentro do Banco de Dados do Room
@TypeConverters(Converters::class)
abstract class AppDataBaseRovers : RoomDatabase() {

    abstract fun roverDao(): RoverDao
}
