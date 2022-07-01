package com.wdretzer.nasaprojetointegrador.bancodadosfav

import android.content.Context
import androidx.room.Room

object DataBaseFactoryFav {

    private var instance: AppDataBaseFav? = null

    fun getDataBaseFav() = instance ?: throw IllegalStateException("Database is not initialized")

    fun build(context: Context): AppDataBaseFav {

        val currentInstance = instance
        if (currentInstance != null) return currentInstance

        val dataBase = Room.databaseBuilder(
            context.applicationContext,
            AppDataBaseFav::class.java,
            "Favoritos"
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
