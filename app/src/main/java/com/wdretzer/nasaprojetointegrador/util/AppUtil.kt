package com.wdretzer.nasaprojetointegrador.util

import android.app.Application
import android.content.Context
import com.facebook.appevents.AppEventsLogger
import com.wdretzer.nasaprojetointegrador.bancodadosnasa.DataBaseFactory
import com.wdretzer.nasaprojetointegrador.bancodadosfav.DataBaseFactoryFav
import com.wdretzer.nasaprojetointegrador.bancodadosrover.DataBaseFactoryRover

class AppUtil: Application() {

    override fun onCreate() {
        super.onCreate()

        DataBaseFactory.build(this)
        DataBaseFactoryFav.build(this)
        DataBaseFactoryRover.build(this)
        appContext = applicationContext
        AppEventsLogger.activateApp(this)
    }

    companion object{
        var appContext: Context? = null
    }
}
