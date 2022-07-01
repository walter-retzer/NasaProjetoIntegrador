package com.wdretzer.nasaprojetointegrador.bancodadosrover

import android.content.Context
import androidx.room.Room

object DataBaseFactoryRover {

    private var instance: AppDataBaseRovers? = null

    fun getDataBaseRover() = instance ?: throw IllegalStateException("Database is not initialized")

    fun build(context: Context): AppDataBaseRovers {

        val currentInstance = instance
        if (currentInstance != null) return currentInstance

        val dataBase = Room.databaseBuilder(
            context.applicationContext,
            AppDataBaseRovers::class.java,
            "ROVERS_IMGs"
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
