package com.wdretzer.nasaprojetointegrador.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.annotation.StringRes
import com.wdretzer.nasaprojetointegrador.R

class SharedPref(
    val context: Context,
    @StringRes private val prefFile: Int = R.string.sharedpref
) {

    val sharedPref: SharedPreferences = context.getSharedPreferences(
        context.getString(prefFile), MODE_PRIVATE
    )

    inline fun <reified T> get(key: String): T = sharedPref.run {
        when (T::class) {
            Boolean::class -> getBoolean(key, false) as T
            Float::class -> getFloat(key, 0f) as T
            Int::class -> getInt(key, 0) as T
            Long::class -> getLong(key, 0L) as T
            String::class -> getString(key, "") as T
            else -> getStringSet(key, setOf("")) as T
        }
    }

    fun <T> put(key: String, value: T) = sharedPref.edit().run {
        when (value) {
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            else -> putStringSet(key, value as Set<String>)
        }
        apply()
    }
}
