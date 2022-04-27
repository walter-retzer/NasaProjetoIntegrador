package com.wdretzer.nasaprojetointegrador.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ/\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/data/Nasa;", "", "getDataNasa", "Lcom/wdretzer/nasaprojetointegrador/data/NasaRequest;", "search", "", "page", "", "type", "(Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface Nasa {
    @org.jetbrains.annotations.NotNull
    public static final com.wdretzer.nasaprojetointegrador.data.Nasa.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "search?")
    public abstract java.lang.Object getDataNasa(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "q")
    java.lang.String search, @retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "media_type")
    java.lang.String type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.wdretzer.nasaprojetointegrador.data.NasaRequest> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/data/Nasa$Companion;", "", "()V", "api", "Lcom/wdretzer/nasaprojetointegrador/data/Nasa;", "getApi", "()Lcom/wdretzer/nasaprojetointegrador/data/Nasa;", "api$delegate", "Lkotlin/Lazy;", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull
        private static final kotlin.Lazy api$delegate = null;
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.wdretzer.nasaprojetointegrador.data.Nasa getApi() {
            return null;
        }
    }
}