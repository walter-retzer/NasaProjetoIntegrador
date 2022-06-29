package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.*
import com.wdretzer.nasaprojetointegrador.data.ItensData

@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg nasaEntity: NasaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAll(nasaEntity: List<NasaEntity>)

    @Delete
    fun delete(vararg nasaEntity: NasaEntity)

    @Update
    fun update(vararg nasaEntity: NasaEntity)

    @Query("SELECT * FROM nasaRoverApp ORDER BY id DESC")
    fun listAll(): List<NasaEntity>

    @Query("SELECT * FROM nasaRoverApp WHERE id = :id")
    fun retrieveById(id: Int): NasaEntity

    @Query("SELECT * FROM nasaRoverApp WHERE data = :apiData")
    fun retrieveByApiId(apiData: List<ItensData>) : NasaEntity

    @Query("SELECT COUNT(data) FROM nasaRoverApp WHERE data = :apiData")
    fun countApiId(apiData: List<ItensData>) : Int

//    @Query("SELECT COUNT(data) FROM nasaApp WHERE img_src = :apiData")
//    fun countApiIdRover(apiData: String) : Int

    @Query("DELETE FROM nasaRoverApp WHERE data = :apiData")
    fun deleteByApiId(apiData: List<ItensData>)

//    @Query("DELETE FROM nasaApp WHERE img_src = :apiData")
//    fun deleteByApiIdRover(apiData: String)

    @Query("DELETE FROM nasaRoverApp WHERE data = :apiData")
    fun deleteAllbyApi(apiData: List<ItensData>)

}
