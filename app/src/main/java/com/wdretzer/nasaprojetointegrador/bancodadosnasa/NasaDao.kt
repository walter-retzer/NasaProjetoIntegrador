package com.wdretzer.nasaprojetointegrador.bancodadosnasa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wdretzer.nasaprojetointegrador.data.ItensData

@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg nasaEntity: NasaEntity)

    @Query("SELECT * FROM nasaFav ORDER BY id DESC")
    fun listAll(): List<NasaEntity>

    @Query("SELECT COUNT(data) FROM nasaFav WHERE data = :apiData")
    fun countApiId(apiData: List<ItensData>) : Int

    @Query("DELETE FROM nasaFav WHERE data = :apiData")
    fun deleteByApiId(apiData: List<ItensData>)

}
