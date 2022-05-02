package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wdretzer.nasaprojetointegrador.data.ItensData
import com.wdretzer.nasaprojetointegrador.data.Links


@Entity(tableName = "nasaApp")
class NasaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val href: String = " ",
    @ColumnInfo
    val data: List<ItensData>,
    @ColumnInfo
    val links: List<Links>,
)


// Classe TypeConvert para converter as listas para dentro do Banco de Dados do Room
class Converters {

    // Funções para converter as Listas da Classe Itens Data
    @TypeConverter
    fun listToJsonString(value: List<ItensData>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<ItensData>::class.java).toList()

    // Funções para converter as Listas da Classe Links
    @TypeConverter
    fun listToJsonString2(value: List<Links>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList2(value: String) = Gson().fromJson(value, Array<Links>::class.java).toList()
}
