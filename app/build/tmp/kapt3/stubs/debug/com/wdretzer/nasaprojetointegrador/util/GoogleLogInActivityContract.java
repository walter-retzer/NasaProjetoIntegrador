package com.wdretzer.nasaprojetointegrador.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u001a\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0016\u00a8\u0006\u0013"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;", "Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "handleSignInResult", "completedTask", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "parseResult", "resultCode", "", "intent", "Result", "app_debug"})
public final class GoogleLogInActivityContract extends androidx.activity.result.contract.ActivityResultContract<com.google.android.gms.auth.api.signin.GoogleSignInOptions, com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result> {
    
    public GoogleLogInActivityContract() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.content.Intent createIntent(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.google.android.gms.auth.api.signin.GoogleSignInOptions input) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result parseResult(int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    private final com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result handleSignInResult(com.google.android.gms.tasks.Task<com.google.android.gms.auth.api.signin.GoogleSignInAccount> completedTask) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result;", "", "()V", "Error", "Success", "Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result$Success;", "Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result$Error;", "app_debug"})
    public static abstract class Result {
        
        private Result() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result$Success;", "Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result;", "googleSignInAccount", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "(Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;)V", "getGoogleSignInAccount", "()Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class Success extends com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result {
            @org.jetbrains.annotations.NotNull
            private final com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount = null;
            
            @org.jetbrains.annotations.NotNull
            public final com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result.Success copy(@org.jetbrains.annotations.NotNull
            com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
                return null;
            }
            
            @java.lang.Override
            public boolean equals(@org.jetbrains.annotations.Nullable
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull
            @java.lang.Override
            public java.lang.String toString() {
                return null;
            }
            
            public Success(@org.jetbrains.annotations.NotNull
            com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.google.android.gms.auth.api.signin.GoogleSignInAccount component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.google.android.gms.auth.api.signin.GoogleSignInAccount getGoogleSignInAccount() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u00a2\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004H\u00c6\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result$Error;", "Lcom/wdretzer/nasaprojetointegrador/util/GoogleLogInActivityContract$Result;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class Error extends com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result {
            @org.jetbrains.annotations.NotNull
            private final java.lang.Exception exception = null;
            
            @org.jetbrains.annotations.NotNull
            public final com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract.Result.Error copy(@org.jetbrains.annotations.NotNull
            java.lang.Exception exception) {
                return null;
            }
            
            @java.lang.Override
            public boolean equals(@org.jetbrains.annotations.Nullable
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull
            @java.lang.Override
            public java.lang.String toString() {
                return null;
            }
            
            public Error(@org.jetbrains.annotations.NotNull
            java.lang.Exception exception) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull
            public final java.lang.Exception component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final java.lang.Exception getException() {
                return null;
            }
        }
    }
}