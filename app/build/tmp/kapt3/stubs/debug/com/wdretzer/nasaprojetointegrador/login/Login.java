package com.wdretzer.nasaprojetointegrador.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020.H\u0002J\b\u00100\u001a\u00020.H\u0002J\b\u00101\u001a\u00020.H\u0002J\u0010\u00102\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0002J\u0012\u00105\u001a\u00020.2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020.H\u0002J\b\u00109\u001a\u00020.H\u0002J\b\u0010:\u001a\u00020.H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001b\u0010\u000e\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u000f\u0010\bR\u001b\u0010\u0011\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0012\u0010\bR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0010\u0012\f\u0012\n  *\u0004\u0018\u00010\u001b0\u001b0\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n  *\u0004\u0018\u00010\"0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010\'\u001a\u00020(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010*\u00a8\u0006<"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/login/Login;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "buttonCadastrar", "Landroid/widget/Button;", "getButtonCadastrar", "()Landroid/widget/Button;", "buttonCadastrar$delegate", "Lkotlin/Lazy;", "buttonFacebook", "getButtonFacebook", "buttonFacebook$delegate", "buttonGoogle", "getButtonGoogle", "buttonGoogle$delegate", "buttonLogin", "getButtonLogin", "buttonLogin$delegate", "callbackManager", "Lcom/facebook/CallbackManager;", "dialogCorrect", "Lcom/wdretzer/nasaprojetointegrador/dialogfragments/DialogFragmentCadastro;", "getDialogCorrect", "()Lcom/wdretzer/nasaprojetointegrador/dialogfragments/DialogFragmentCadastro;", "googleSignInOptions", "Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;", "getGoogleSignInOptions", "()Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;", "googleSignInRequest", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "loginManager", "Lcom/facebook/login/LoginManager;", "progressBar", "Landroid/widget/FrameLayout;", "getProgressBar", "()Landroid/widget/FrameLayout;", "textEmail", "Landroid/widget/EditText;", "getTextEmail", "()Landroid/widget/EditText;", "textPassword", "getTextPassword", "checkDados", "", "checkLoginUser", "keyHashFacebook", "loginFacebook", "loginGoogle", "result", "Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "registerFacebbokCallback", "sendToCadastroUsuario", "sendToInicioGuia", "Companion", "app_debug"})
public final class Login extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy buttonLogin$delegate = null;
    private final kotlin.Lazy buttonCadastrar$delegate = null;
    private final kotlin.Lazy buttonGoogle$delegate = null;
    private final kotlin.Lazy buttonFacebook$delegate = null;
    private com.google.firebase.auth.FirebaseAuth auth;
    @org.jetbrains.annotations.NotNull
    private final com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentCadastro dialogCorrect = null;
    private final androidx.activity.result.ActivityResultLauncher<com.google.android.gms.auth.api.signin.GoogleSignInOptions> googleSignInRequest = null;
    private final com.facebook.login.LoginManager loginManager = null;
    private final com.facebook.CallbackManager callbackManager = null;
    @org.jetbrains.annotations.NotNull
    public static final com.wdretzer.nasaprojetointegrador.login.Login.Companion Companion = null;
    private static final java.util.List<java.lang.String> permissions = null;
    
    public Login() {
        super();
    }
    
    private final android.widget.Button getButtonLogin() {
        return null;
    }
    
    private final android.widget.Button getButtonCadastrar() {
        return null;
    }
    
    private final android.widget.Button getButtonGoogle() {
        return null;
    }
    
    private final android.widget.Button getButtonFacebook() {
        return null;
    }
    
    private final android.widget.EditText getTextEmail() {
        return null;
    }
    
    private final android.widget.EditText getTextPassword() {
        return null;
    }
    
    private final android.widget.FrameLayout getProgressBar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentCadastro getDialogCorrect() {
        return null;
    }
    
    private final com.google.android.gms.auth.api.signin.GoogleSignInOptions getGoogleSignInOptions() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void checkDados() {
    }
    
    private final void checkLoginUser() {
    }
    
    private final void sendToInicioGuia() {
    }
    
    private final void sendToCadastroUsuario() {
    }
    
    private final void keyHashFacebook() {
    }
    
    private final void registerFacebbokCallback() {
    }
    
    private final void loginFacebook() {
    }
    
    private final void loginGoogle(com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result result) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/login/Login$Companion;", "", "()V", "permissions", "", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}