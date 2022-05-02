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

    @Query("SELECT * FROM nasaApp ORDER BY id DESC")
    fun listAll(): List<NasaEntity>

    @Query("SELECT * FROM nasaApp WHERE id = :id")
    fun retrieveById(id: Int): NasaEntity

    @Query("SELECT * FROM nasaApp WHERE data = :apiData")
    fun retrieveByApiId(apiData: List<ItensData>) : NasaEntity

    @Query("SELECT COUNT(data) FROM nasaApp WHERE data = :apiData")
    fun countApiId(apiData: List<ItensData>) : Int

    @Query("DELETE FROM nasaApp WHERE data = :apiData")
    fun deleteByApiId(apiData: List<ItensData>)

    @Query("DELETE FROM nasaApp WHERE data = :apiData")
    fun deleteAllbyApi(apiData: List<ItensData>)

}
