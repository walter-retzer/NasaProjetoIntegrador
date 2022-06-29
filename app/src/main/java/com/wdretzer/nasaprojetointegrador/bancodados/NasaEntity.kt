package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wdretzer.nasaprojetointegrador.data.*


@Entity(tableName = "nasaRoverApp")
class NasaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val href: String = " ",
    @ColumnInfo
    val data: List<ItensData>,
    @ColumnInfo
    val links: List<Links>,




    @ColumnInfo
    val idRover: String = " ",

    @ColumnInfo
    val sol: Int = 0,

    @ColumnInfo
    val cameras:  RoverCamera,

    @ColumnInfo
    val img_src: String = " ",

    @ColumnInfo
    val earth_date: String = " ",

    @ColumnInfo
    val rover:  RoverInfo,





)


// Classe TypeConvert para converter as listas para dentro do Banco de Dados do Room
class Converters {

    // Funções para converter as Listas da Classe Itens Data
    @TypeConverter
    fun listToJsonString(value: List<ItensData>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) =
        Gson().fromJson(value, Array<ItensData>::class.java).toList()

    // Funções para converter as Listas da Classe Links
    @TypeConverter
    fun listToJsonString2(value: List<Links>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList2(value: String) = Gson().fromJson(value, Array<Links>::class.java).toList()



    // Funções para converter as Listas da Classe Links
    @TypeConverter
    fun listToJsonString3(value: RoverCamera?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList3(value: String) = Gson().fromJson(value, RoverCamera::class.java)


    // Funções para converter as Listas da Classe Links
    @TypeConverter
    fun listToJsonString4(value: RoverInfo?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList4(value: String) = Gson().fromJson(value, RoverInfo::class.java)


//
//
//    // Funções para converter as Listas da Classe Links
//    @TypeConverter
//    fun listToJsonString5(value: RoverItens?): String = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonStringToList5(value: String) = Gson().fromJson(value, RoverItens::class.java)
//
//    // Funções para converter as Listas da Classe Links
//    @TypeConverter
//    fun listToJsonString6(value: NasaItens?): String = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonStringToList6(value: String) = Gson().fromJson(value, NasaItens::class.java)



}
