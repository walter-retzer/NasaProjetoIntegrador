package com.wdretzer.nasaprojetointegrador.bancodadosnasa

import android.content.Context
import androidx.room.Room

object DataBaseFactory {

    private var instance: AppDataBase? = null

    fun getDataBaseNasa() = instance ?: throw IllegalStateException("Database is not initialized")

    fun build(context: Context): AppDataBase {

        val currentInstance = instance
        if (currentInstance != null) return currentInstance

        val dataBase = Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "NASA_IMGs"
        )

        dataBase.allowMainThreadQueries()
        val dbInstance = dataBase.build()
        instance = dbInstance

        return dbInstance
    }

    fun removeInstance(){
        instance = null
    }
}
