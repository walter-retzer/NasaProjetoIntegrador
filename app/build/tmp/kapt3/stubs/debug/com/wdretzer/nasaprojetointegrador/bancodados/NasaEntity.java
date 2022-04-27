package com.wdretzer.nasaprojetointegrador.bancodados;

import java.lang.System;

@androidx.room.Entity(tableName = "nasaBD")
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/bancodados/NasaEntity;", "", "id", "", "href", "", "data", "", "Lcom/wdretzer/nasaprojetointegrador/data/ItensData;", "links", "Lcom/wdretzer/nasaprojetointegrador/data/Links;", "(ILjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getHref", "()Ljava/lang/String;", "getId", "()I", "getLinks", "app_debug"})
public final class NasaEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.lang.String href = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.util.List<com.wdretzer.nasaprojetointegrador.data.ItensData> data = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.ColumnInfo
    private final java.util.List<com.wdretzer.nasaprojetointegrador.data.Links> links = null;
    
    public NasaEntity(int id, @org.jetbrains.annotations.NotNull
    java.lang.String href, @org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.ItensData> data, @org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.Links> links) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getHref() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.wdretzer.nasaprojetointegrador.data.ItensData> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.wdretzer.nasaprojetointegrador.data.Links> getLinks() {
        return null;
    }
}