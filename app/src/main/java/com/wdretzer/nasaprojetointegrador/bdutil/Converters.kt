package com.wdretzer.nasaprojetointegrador.bdutil

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wdretzer.nasaprojetointegrador.data.ItensData
import com.wdretzer.nasaprojetointegrador.data.Links
import com.wdretzer.nasaprojetointegrador.data.RoverCamera
import com.wdretzer.nasaprojetointegrador.data.RoverInfo

// Classe TypeConvert para converter as listas para dentro do Banco de Dados do Room
class Converters {
    // Funções para converter as Listas da Classe Itens Data
    @TypeConverter
    fun listToJsonString(value: List<ItensData>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<ItensData>::class.java).toList()

    // Funções para converter as Listas da Classe Links
    @TypeConverter
    fun listToJsonString1(value: List<Links>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList1(value: String) = Gson().fromJson(value, Array<Links>::class.java).toList()

    // Funções para converter as Listas da Classe Rover Camera
    @TypeConverter
    fun listToJsonString2(value: RoverCamera?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList2(value: String) = Gson().fromJson(value, RoverCamera::class.java)

    // Funções para converter as Listas da Classe Rover Info
    @TypeConverter
    fun listToJsonString3(value: RoverInfo?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList3(value: String) = Gson().fromJson(value, RoverInfo::class.java)
}
