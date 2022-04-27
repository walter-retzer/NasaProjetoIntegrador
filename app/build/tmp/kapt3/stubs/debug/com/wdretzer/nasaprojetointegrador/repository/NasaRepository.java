package com.wdretzer.nasaprojetointegrador.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\r\u001a\u00020\fJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0\nJ \u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00110\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0011J\"\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/repository/NasaRepository;", "", "api", "Lcom/wdretzer/nasaprojetointegrador/data/Nasa;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/wdretzer/nasaprojetointegrador/data/Nasa;Lkotlinx/coroutines/CoroutineDispatcher;)V", "dao", "Lcom/wdretzer/nasaprojetointegrador/bancodados/NasaDao;", "addOrRemoveFavourite", "Lkotlinx/coroutines/flow/Flow;", "Lcom/wdretzer/nasaprojetointegrador/data/DataResult;", "Lcom/wdretzer/nasaprojetointegrador/data/NasaItens;", "item", "getFavourite", "", "itemFav", "", "requestData", "Lcom/wdretzer/nasaprojetointegrador/data/NasaRequest;", "search", "", "page", "", "Companion", "app_debug"})
public final class NasaRepository {
    private final com.wdretzer.nasaprojetointegrador.data.Nasa api = null;
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    private final com.wdretzer.nasaprojetointegrador.bancodados.NasaDao dao = null;
    @org.jetbrains.annotations.NotNull
    public static final com.wdretzer.nasaprojetointegrador.repository.NasaRepository.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlin.Lazy instance$delegate = null;
    
    public NasaRepository() {
        super();
    }
    
    public NasaRepository(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.data.Nasa api, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.CoroutineDispatcher dispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.wdretzer.nasaprojetointegrador.data.DataResult<com.wdretzer.nasaprojetointegrador.data.NasaRequest>> requestData(@org.jetbrains.annotations.NotNull
    java.lang.String search, int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wdretzer.nasaprojetointegrador.data.NasaItens>> itemFav(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.data.NasaItens> item) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.wdretzer.nasaprojetointegrador.data.NasaItens>> getFavourite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.wdretzer.nasaprojetointegrador.data.DataResult<com.wdretzer.nasaprojetointegrador.data.NasaItens>> addOrRemoveFavourite(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.data.NasaItens item) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/repository/NasaRepository$Companion;", "", "()V", "instance", "Lcom/wdretzer/nasaprojetointegrador/repository/NasaRepository;", "getInstance", "()Lcom/wdretzer/nasaprojetointegrador/repository/NasaRepository;", "instance$delegate", "Lkotlin/Lazy;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.wdretzer.nasaprojetointegrador.repository.NasaRepository getInstance() {
            return null;
        }
    }
}