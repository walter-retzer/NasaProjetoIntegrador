package com.wdretzer.nasaprojetointegrador.bancodados;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'J!\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\'\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'J!\u0010\u000e\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\'\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u000f\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005H\'J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005H\'J\u0016\u0010\u0011\u001a\u00020\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0003H\'J!\u0010\u0014\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\'\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/bancodados/NasaDao;", "", "countApiId", "", "apiData", "", "Lcom/wdretzer/nasaprojetointegrador/data/ItensData;", "delete", "", "nasaEntity", "", "Lcom/wdretzer/nasaprojetointegrador/bancodados/NasaEntity;", "([Lcom/wdretzer/nasaprojetointegrador/bancodados/NasaEntity;)V", "deleteByApiId", "insert", "insertAll", "listAll", "retrieveByApiId", "retrieveById", "id", "update", "app_debug"})
public abstract interface NasaDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insert(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity... nasaEntity);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity> nasaEntity);
    
    @androidx.room.Delete
    public abstract void delete(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity... nasaEntity);
    
    @androidx.room.Update
    public abstract void update(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity... nasaEntity);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM nasaBD ORDER BY id DESC")
    public abstract java.util.List<com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity> listAll();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM nasaBD WHERE id = :id")
    public abstract com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity retrieveById(int id);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM nasaBD WHERE data = :apiData")
    public abstract com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity retrieveByApiId(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.ItensData> apiData);
    
    @androidx.room.Query(value = "SELECT COUNT(data) FROM nasaBD WHERE data = :apiData")
    public abstract int countApiId(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.ItensData> apiData);
    
    @androidx.room.Query(value = "DELETE FROM nasaBD WHERE data = :apiData")
    public abstract void deleteByApiId(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.ItensData> apiData);
}