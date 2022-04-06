package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wdretzer.nasaprojetointegrador.data.DataItens
import com.wdretzer.nasaprojetointegrador.data.NasaNextPage


@Entity(tableName = "nasaBD")
data class NasaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(defaultValue = "gdgf")
    val href: String,

    @ColumnInfo(defaultValue = "jdjfj")
    val version: String,

    @ColumnInfo
    val items: List<DataItens> ,

    @ColumnInfo
    val links: List<NasaNextPage> ,

//    @ColumnInfo
//    val metadata: NasaMetadata = NasaMetadata(0)

)


class Converters {

    @TypeConverter
    fun listToJsonString(value: List<DataItens>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<DataItens>::class.java).toList()


    @TypeConverter
    fun listToJsonString2(value: List<NasaNextPage>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList2(value: String) = Gson().fromJson(value, Array<NasaNextPage>::class.java).toList()
}

//data class StatesHolder(
//    val stateItensList: List<DataItens>
//)
//
//class Converters {
//
//    @TypeConverter
//    fun fromStatesHolder(sh: StatesHolder): String {
//        return Gson().toJson(sh)
//    }
//    @TypeConverter
//    fun toStatesHolder(sh: String): StatesHolder {
//        return Gson().fromJson(sh,StatesHolder::class.java)
//    }
//}