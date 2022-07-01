package com.wdretzer.nasaprojetointegrador.bancodadosrover

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg roverEntity: RoverEntity)

    @Query("SELECT * FROM roverFav ORDER BY id DESC")
    fun listAll(): List<RoverEntity>

    @Query("SELECT COUNT(imgRover) FROM roverFav WHERE imgRover = :apiData")
    fun countApiId(apiData: String) : Int

    @Query("DELETE FROM roverFav WHERE imgRover = :apiData")
    fun deleteByApiImgSrc(apiData: String)
}
