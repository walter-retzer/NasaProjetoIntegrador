package com.wdretzer.nasaprojetointegrador.bancodadosrover

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wdretzer.nasaprojetointegrador.data.RoverCamera
import com.wdretzer.nasaprojetointegrador.data.RoverInfo


@Entity(tableName = "roverFav")
class RoverEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val idRover: String = " ",

    @ColumnInfo
    val sol: Int = 0,

    @ColumnInfo
    val cameras: RoverCamera,

    @ColumnInfo
    val imgRover: String = " ",

    @ColumnInfo
    val earthDate: String = " ",

    @ColumnInfo
    val rover: RoverInfo,
)
