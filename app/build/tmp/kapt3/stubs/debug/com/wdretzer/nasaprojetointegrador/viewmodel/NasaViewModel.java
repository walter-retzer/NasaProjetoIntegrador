package com.wdretzer.nasaprojetointegrador.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\nJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r0\bJ \u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u000fJ\"\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/viewmodel/NasaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/wdretzer/nasaprojetointegrador/repository/NasaRepository;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/wdretzer/nasaprojetointegrador/repository/NasaRepository;Lkotlinx/coroutines/CoroutineDispatcher;)V", "addOrRemoveFavourite", "Landroidx/lifecycle/LiveData;", "Lcom/wdretzer/nasaprojetointegrador/data/DataResult;", "Lcom/wdretzer/nasaprojetointegrador/data/NasaItens;", "item", "getFavourite", "", "itemFav", "", "request", "Lcom/wdretzer/nasaprojetointegrador/data/NasaRequest;", "search", "", "page", "", "app_debug"})
public final class NasaViewModel extends androidx.lifecycle.ViewModel {
    private final com.wdretzer.nasaprojetointegrador.repository.NasaRepository repository = null;
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    
    public NasaViewModel() {
        super();
    }
    
    public NasaViewModel(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.repository.NasaRepository repository, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.wdretzer.nasaprojetointegrador.data.DataResult<com.wdretzer.nasaprojetointegrador.data.NasaItens>> addOrRemoveFavourite(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.data.NasaItens item) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.wdretzer.nasaprojetointegrador.data.DataResult<com.wdretzer.nasaprojetointegrador.data.NasaRequest>> request(@org.jetbrains.annotations.NotNull
    java.lang.String search, int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.wdretzer.nasaprojetointegrador.data.NasaItens>> getFavourite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.wdretzer.nasaprojetointegrador.data.NasaItens>> itemFav(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.NasaItens> item) {
        return null;
    }
}