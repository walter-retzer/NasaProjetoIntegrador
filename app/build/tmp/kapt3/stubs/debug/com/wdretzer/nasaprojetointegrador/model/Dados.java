package com.wdretzer.nasaprojetointegrador.model;

import java.lang.System;

@kotlinx.parcelize.Parcelize
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\nH\u00d6\u0001J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/model/Dados;", "Landroid/os/Parcelable;", "image", "", "description", "(Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getImage", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
public final class Dados implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String image = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String description = null;
    public static final android.os.Parcelable.Creator<com.wdretzer.nasaprojetointegrador.model.Dados> CREATOR = null;
    
    public Dados(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.NotNull
    java.lang.String description) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getImage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescription() {
        return null;
    }
    
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<com.wdretzer.nasaprojetointegrador.model.Dados> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final com.wdretzer.nasaprojetointegrador.model.Dados createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public final com.wdretzer.nasaprojetointegrador.model.Dados[] newArray(int size) {
            return null;
        }
    }
}