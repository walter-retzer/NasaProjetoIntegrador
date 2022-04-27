package com.wdretzer.nasaprojetointegrador.perfil;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/perfil/AdapterPerfil;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "listaPerfil", "", "Lcom/wdretzer/nasaprojetointegrador/perfil/DadosPerfil;", "action", "Lkotlin/Function1;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "PerfilViewHolder", "app_debug"})
public final class AdapterPerfil extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final java.util.List<com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil> listaPerfil = null;
    private final kotlin.jvm.functions.Function1<com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil, kotlin.Unit> action = null;
    
    public AdapterPerfil(@org.jetbrains.annotations.NotNull
    java.util.List<com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil> listaPerfil, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil, kotlin.Unit> action) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/perfil/AdapterPerfil$PerfilViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "action", "Lkotlin/Function1;", "Lcom/wdretzer/nasaprojetointegrador/perfil/DadosPerfil;", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "photoPerfil", "Lcom/google/android/material/imageview/ShapeableImageView;", "bind", "item", "app_debug"})
    public static final class PerfilViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final kotlin.jvm.functions.Function1<com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil, kotlin.Unit> action = null;
        private final com.google.android.material.imageview.ShapeableImageView photoPerfil = null;
        
        public PerfilViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil, kotlin.Unit> action) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.wdretzer.nasaprojetointegrador.perfil.DadosPerfil item) {
        }
    }
}