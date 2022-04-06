package com.wdretzer.nasaprojetointegrador.bancodados

import androidx.room.*
import com.wdretzer.nasaprojetointegrador.data.DataItens


@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg nasaEntity: NasaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(nasaEntity: List<NasaEntity>)

    @Delete
    fun delete(vararg nasaEntity: NasaEntity)

    @Update
    fun update(vararg nasaEntity: NasaEntity)

    @Query("SELECT * FROM nasaBD ORDER BY id DESC")
    fun listAll(): List<NasaEntity>
    //suspend fun listAll(): Flow<List<NasaEntity>>

    @Query("SELECT * FROM nasaBD WHERE id = :id")
    fun retrieveById(id: Int): NasaEntity

    @Query("SELECT * FROM nasaBD WHERE items = :apiItems")
    fun retrieveByApiId(apiItems: List<DataItens>): NasaEntity

    @Query("SELECT COUNT(items) FROM nasaBD WHERE items = :apiItems")
    fun countApiId(apiItems: List<DataItens>): Int

    @Query("DELETE FROM nasaBD WHERE items = :apiItems")
    fun deleteByApiId(apiItems: List<DataItens>)

}
