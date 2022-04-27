package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.*
import com.wdretzer.nasaprojetointegrador.data.ItensData


@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(vararg nasaEntity: NasaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAll(nasaEntity: List<NasaEntity>)

    @Delete
    fun delete(vararg nasaEntity: NasaEntity)

    @Update
    fun update(vararg nasaEntity: NasaEntity)

    @Query("SELECT * FROM nasaBD ORDER BY id DESC")
    fun listAll(): List<NasaEntity>

    @Query("SELECT * FROM nasaBD WHERE id = :id")
    fun retrieveById(id: Int): NasaEntity

    @Query("SELECT * FROM nasaBD WHERE data = :apiData")
    fun retrieveByApiId(apiData: List<ItensData>) : NasaEntity

    @Query("SELECT COUNT(data) FROM nasaBD WHERE data = :apiData")
    fun countApiId(apiData: List<ItensData>) : Int

    @Query("DELETE FROM nasaBD WHERE data = :apiData")
    fun deleteByApiId(apiData: List<ItensData>)
}