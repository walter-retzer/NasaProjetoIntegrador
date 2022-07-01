package com.wdretzer.nasaprojetointegrador.bancodadosfav

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wdretzer.nasaprojetointegrador.data.ItensData


@Entity(tableName = "favImgFav")
class FavEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val img: String = " ",

    @ColumnInfo
    val title: String = " ",

    @ColumnInfo
    val data: List<ItensData>,
)
