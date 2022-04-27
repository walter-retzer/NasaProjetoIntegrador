package com.wdretzer.nasaprojetointegrador.imagensnasa;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00152\u0006\u00100\u001a\u000201H\u0002J\u0014\u0010M\u001a\u00020K2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020P0OJ\u0012\u0010Q\u001a\u00020K2\b\u0010R\u001a\u0004\u0018\u00010SH\u0014J\b\u0010T\u001a\u00020KH\u0002J\u0010\u0010U\u001a\u00020K2\u0006\u0010V\u001a\u00020WH\u0002JN\u0010X\u001a\u00020K2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010L\u001a\u00020\u0015H\u0002J\b\u0010[\u001a\u00020KH\u0002J\b\u0010\\\u001a\u00020KH\u0002J\b\u0010]\u001a\u00020KH\u0002J\b\u0010^\u001a\u00020KH\u0002J\b\u0010_\u001a\u00020KH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001b\u0010\u000e\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u000f\u0010\bR\u001b\u0010\u0011\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0012\u0010\bR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001c\u0010 \u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001c\u0010#\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u0014\u0010&\u001a\u00020\'8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0014\u00106\u001a\u0002078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b8\u00109R\u001a\u0010:\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0017\"\u0004\b<\u0010\u0019R\u001a\u0010=\u001a\u000201X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u00103\"\u0004\b?\u00105R\u001b\u0010@\u001a\u00020A8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bD\u0010\n\u001a\u0004\bB\u0010CR\u001b\u0010E\u001a\u00020F8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bI\u0010\n\u001a\u0004\bG\u0010H\u00a8\u0006`"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/imagensnasa/ImgensNasa;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adp", "Lcom/wdretzer/nasaprojetointegrador/recyclerview/ImagensAdpter;", "buttonHomePlanets", "Landroid/widget/ImageView;", "getButtonHomePlanets", "()Landroid/widget/ImageView;", "buttonHomePlanets$delegate", "Lkotlin/Lazy;", "buttonMenuFavoritos", "getButtonMenuFavoritos", "buttonMenuFavoritos$delegate", "buttonMenuPerfil", "getButtonMenuPerfil", "buttonMenuPerfil$delegate", "buttonPesquisaImagens", "getButtonPesquisaImagens", "buttonPesquisaImagens$delegate", "criadores", "", "getCriadores", "()Ljava/lang/String;", "setCriadores", "(Ljava/lang/String;)V", "date", "getDate", "setDate", "description", "getDescription", "setDescription", "imagem", "getImagem", "setImagem", "keywords", "getKeywords", "setKeywords", "loading", "Landroid/widget/FrameLayout;", "getLoading", "()Landroid/widget/FrameLayout;", "nextPage", "", "getNextPage", "()Z", "setNextPage", "(Z)V", "page", "", "getPage", "()I", "setPage", "(I)V", "recycler", "Landroidx/recyclerview/widget/RecyclerView;", "getRecycler", "()Landroidx/recyclerview/widget/RecyclerView;", "setSearchText", "getSetSearchText", "setSetSearchText", "totalImagens", "getTotalImagens", "setTotalImagens", "totalItens", "Landroid/widget/TextView;", "getTotalItens", "()Landroid/widget/TextView;", "totalItens$delegate", "viewModelNasa", "Lcom/wdretzer/nasaprojetointegrador/viewmodel/NasaViewModel;", "getViewModelNasa", "()Lcom/wdretzer/nasaprojetointegrador/viewmodel/NasaViewModel;", "viewModelNasa$delegate", "chamadas", "", "search", "oberservarNasa", "result", "Lcom/wdretzer/nasaprojetointegrador/data/DataResult;", "Lcom/wdretzer/nasaprojetointegrador/data/NasaRequest;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "recyclerView", "saveFavourite", "item", "Lcom/wdretzer/nasaprojetointegrador/data/NasaItens;", "sendToDetalheImage", "creator", "keyword", "sendToFavoritos", "sendToHomePlanets", "sendToPerfil", "sendToSearchImage", "setScrollView", "app_debug"})
public final class ImgensNasa extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy buttonHomePlanets$delegate = null;
    private final kotlin.Lazy buttonPesquisaImagens$delegate = null;
    private final kotlin.Lazy buttonMenuFavoritos$delegate = null;
    private final kotlin.Lazy buttonMenuPerfil$delegate = null;
    private final kotlin.Lazy totalItens$delegate = null;
    private final kotlin.Lazy viewModelNasa$delegate = null;
    private com.wdretzer.nasaprojetointegrador.recyclerview.ImagensAdpter adp;
    private int page = 1;
    private int totalImagens = 0;
    private boolean nextPage = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.String description;
    @org.jetbrains.annotations.Nullable
    private java.lang.String imagem;
    @org.jetbrains.annotations.Nullable
    private java.lang.String date;
    @org.jetbrains.annotations.Nullable
    private java.lang.String criadores;
    @org.jetbrains.annotations.Nullable
    private java.lang.String keywords;
    @org.jetbrains.annotations.NotNull
    private java.lang.String setSearchText = "";
    
    public ImgensNasa() {
        super();
    }
    
    private final android.widget.ImageView getButtonHomePlanets() {
        return null;
    }
    
    private final android.widget.ImageView getButtonPesquisaImagens() {
        return null;
    }
    
    private final android.widget.ImageView getButtonMenuFavoritos() {
        return null;
    }
    
    private final android.widget.ImageView getButtonMenuPerfil() {
        return null;
    }
    
    private final android.widget.TextView getTotalItens() {
        return null;
    }
    
    private final com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel getViewModelNasa() {
        return null;
    }
    
    private final android.widget.FrameLayout getLoading() {
        return null;
    }
    
    private final androidx.recyclerview.widget.RecyclerView getRecycler() {
        return null;
    }
    
    public final int getPage() {
        return 0;
    }
    
    public final void setPage(int p0) {
    }
    
    public final int getTotalImagens() {
        return 0;
    }
    
    public final void setTotalImagens(int p0) {
    }
    
    public final boolean getNextPage() {
        return false;
    }
    
    public final void setNextPage(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final void setDescription(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImagem() {
        return null;
    }
    
    public final void setImagem(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDate() {
        return null;
    }
    
    public final void setDate(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCriadores() {
        return null;
    }
    
    public final void setCriadores(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getKeywords() {
        return null;
    }
    
    public final void setKeywords(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSetSearchText() {
        return null;
    }
    
    public final void setSetSearchText(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void chamadas(java.lang.String search, int page) {
    }
    
    private final void recyclerView() {
    }
    
    private final void saveFavourite(com.wdretzer.nasaprojetointegrador.data.NasaItens item) {
    }
    
    public final void oberservarNasa(@org.jetbrains.annotations.NotNull
    com.wdretzer.nasaprojetointegrador.data.DataResult<com.wdretzer.nasaprojetointegrador.data.NasaRequest> result) {
    }
    
    private final void setScrollView() {
    }
    
    private final void sendToDetalheImage(java.lang.String description, java.lang.String imagem, java.lang.String date, java.lang.String creator, java.lang.String keyword, java.lang.String search) {
    }
    
    private final void sendToHomePlanets() {
    }
    
    private final void sendToSearchImage() {
    }
    
    private final void sendToFavoritos() {
    }
    
    private final void sendToPerfil() {
    }
}