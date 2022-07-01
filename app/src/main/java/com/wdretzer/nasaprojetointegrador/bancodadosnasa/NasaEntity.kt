package com.wdretzer.nasaprojetointegrador.bancodadosnasa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wdretzer.nasaprojetointegrador.data.ItensData
import com.wdretzer.nasaprojetointegrador.data.Links


@Entity(tableName = "nasaFav")
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
