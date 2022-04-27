package com.wdretzer.nasaprojetointegrador.util

import android.app.Application
import android.content.Context
import com.facebook.appevents.AppEventsLogger
import com.wdretzer.nasaprojetointegrador.bancodados.DataBaseFactory

class AppUtil: Application() {

    override fun onCreate() {
        super.onCreate()
        DataBaseFactory.build(this)
        appContext = applicationContext
        AppEventsLogger.activateApp(this)
    }

    companion object{
        var appContext: Context? = null
    }
}
