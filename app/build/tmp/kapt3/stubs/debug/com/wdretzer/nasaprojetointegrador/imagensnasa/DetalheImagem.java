package com.wdretzer.nasaprojetointegrador.imagensnasa;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00101\u001a\u000202H\u0002J\u0012\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106H\u0015J\b\u00107\u001a\u000204H\u0003J\b\u00108\u001a\u000204H\u0002J\b\u00109\u001a\u000204H\u0002J\b\u0010:\u001a\u000204H\u0002J\u0018\u0010;\u001a\u0002042\u0006\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020&H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u000f\u0010\fR\u001b\u0010\u0011\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0012\u0010\fR\u001b\u0010\u0014\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0015\u0010\fR\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\b\u001a\u0004\b\u001d\u0010\u001aR\u001b\u0010\u001f\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\b\u001a\u0004\b \u0010\fR\u001b\u0010\"\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010\b\u001a\u0004\b#\u0010\u001aR\u000e\u0010%\u001a\u00020&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u00020&X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001b\u0010.\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b0\u0010\b\u001a\u0004\b/\u0010\u001a\u00a8\u0006>"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/imagensnasa/DetalheImagem;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "animationView", "Lcom/airbnb/lottie/LottieAnimationView;", "getAnimationView", "()Lcom/airbnb/lottie/LottieAnimationView;", "animationView$delegate", "Lkotlin/Lazy;", "buttonMenuPlanets", "Landroid/widget/ImageView;", "getButtonMenuPlanets", "()Landroid/widget/ImageView;", "buttonMenuPlanets$delegate", "buttonPesquisaImagem", "getButtonPesquisaImagem", "buttonPesquisaImagem$delegate", "buttonSaveImage", "getButtonSaveImage", "buttonSaveImage$delegate", "buttonShareImage", "getButtonShareImage", "buttonShareImage$delegate", "criadoresDetalhe", "Landroid/widget/TextView;", "getCriadoresDetalhe", "()Landroid/widget/TextView;", "criadoresDetalhe$delegate", "dataDetalhe", "getDataDetalhe", "dataDetalhe$delegate", "imagemDetalhe", "getImagemDetalhe", "imagemDetalhe$delegate", "keywordsDetalhe", "getKeywordsDetalhe", "keywordsDetalhe$delegate", "setImg", "", "setSearch", "setTitle", "strTranslate", "getStrTranslate", "()Ljava/lang/String;", "setStrTranslate", "(Ljava/lang/String;)V", "textoDetalhe", "getTextoDetalhe", "textoDetalhe$delegate", "getDisc", "Ljava/io/File;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveImage", "sendToHomePlanets", "sendToSearchImage", "shareImage", "translate", "str", "type", "app_debug"})
public final class DetalheImagem extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull
    private java.lang.String strTranslate = "";
    private final kotlin.Lazy animationView$delegate = null;
    private final kotlin.Lazy buttonMenuPlanets$delegate = null;
    private final kotlin.Lazy buttonPesquisaImagem$delegate = null;
    private final kotlin.Lazy buttonShareImage$delegate = null;
    private final kotlin.Lazy buttonSaveImage$delegate = null;
    private final kotlin.Lazy imagemDetalhe$delegate = null;
    private final kotlin.Lazy textoDetalhe$delegate = null;
    private final kotlin.Lazy dataDetalhe$delegate = null;
    private final kotlin.Lazy criadoresDetalhe$delegate = null;
    private final kotlin.Lazy keywordsDetalhe$delegate = null;
    private java.lang.String setSearch = "";
    private java.lang.String setTitle = "";
    private java.lang.String setImg = "";
    
    public DetalheImagem() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStrTranslate() {
        return null;
    }
    
    public final void setStrTranslate(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    private final com.airbnb.lottie.LottieAnimationView getAnimationView() {
        return null;
    }
    
    private final android.widget.ImageView getButtonMenuPlanets() {
        return null;
    }
    
    private final android.widget.ImageView getButtonPesquisaImagem() {
        return null;
    }
    
    private final android.widget.ImageView getButtonShareImage() {
        return null;
    }
    
    private final android.widget.ImageView getButtonSaveImage() {
        return null;
    }
    
    private final android.widget.ImageView getImagemDetalhe() {
        return null;
    }
    
    private final android.widget.TextView getTextoDetalhe() {
        return null;
    }
    
    private final android.widget.TextView getDataDetalhe() {
        return null;
    }
    
    private final android.widget.TextView getCriadoresDetalhe() {
        return null;
    }
    
    private final android.widget.TextView getKeywordsDetalhe() {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    @android.annotation.SuppressLint(value = {"SimpleDateFormat"})
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void translate(java.lang.String str, java.lang.String type) {
    }
    
    private final void sendToHomePlanets() {
    }
    
    private final void sendToSearchImage() {
    }
    
    private final void shareImage() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.N)
    @android.annotation.SuppressLint(value = {"SimpleDateFormat"})
    private final void saveImage() {
    }
    
    private final java.io.File getDisc() {
        return null;
    }
}