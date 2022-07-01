package com.wdretzer.nasaprojetointegrador.bancodadosfav

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg nasaFav: FavEntity)

    @Query("SELECT * FROM favImgFav ORDER BY id DESC")
    fun listAll(): List<FavEntity>

    @Query("SELECT COUNT(img) FROM favImgFav WHERE img = :apiData")
    fun countApiId(apiData: String) : Int

    @Query("DELETE FROM favImgFav WHERE img = :apiData")
    fun deleteByApiImg(apiData: String)
}
