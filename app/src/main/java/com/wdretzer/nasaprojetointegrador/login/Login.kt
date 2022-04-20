package com.wdretzer.nasaprojetointegrador.login

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.cadastro.CadastroUsuario
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia
import com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract
import java.security.MessageDigest


class Login : AppCompatActivity() {

    private val buttonLogin: Button by lazy { findViewById(R.id.btn_login) }
    private val buttonCadastrar: Button by lazy { findViewById(R.id.btn_cadastrar) }
    private val buttonGoogle: Button by lazy { findViewById(R.id.btn_google) }
    private val buttonFacebook: Button by lazy { findViewById(R.id.btn_facebook) }

    private val googleSignInRequest = registerForActivityResult(
        GoogleLogInActivityContract(),
        ::loginGoogle
    )

    private val googleSignInOptions: GoogleSignInOptions
        get() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("935404978331-0bi6cc9t0f7jbmvl57mirm5tid5m16nj.apps.googleusercontent.com")
            .requestEmail()
            .requestProfile()
            .build()

    private val loginManager = LoginManager.getInstance()
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val info: PackageInfo = getPackageManager().getPackageInfo(
            "com.wdretzer.nasaprojetointegrador",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }

        buttonLogin.setOnClickListener {
            val intent = Intent(this, InicioGuia::class.java)
            startActivity(intent)
        }

        buttonCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroUsuario::class.java)
            startActivity(intent)
        }

        buttonGoogle.setOnClickListener { googleSignInRequest.launch(googleSignInOptions) }
        buttonFacebook.setOnClickListener { loginFacebook() }
        registerFacebbokCallback()
    }

    private fun registerFacebbokCallback() {
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(
                    this@Login,
                    "Deu erro ao fazer login com o Facebook!!",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onSuccess(result: LoginResult) {
                val token = result.accessToken.token
                Toast.makeText(this@Login, "Deu certo!! Token Facebook: $token", Toast.LENGTH_LONG)
                    .show()
                sendToInicioGuia()
            }
        })
    }

    private fun loginFacebook() {
        loginManager.logInWithReadPermissions(
            this,
            callbackManager,
            permissions
        )
    }

    private fun loginGoogle(result: GoogleLogInActivityContract.Result) {
        if (result is GoogleLogInActivityContract.Result.Success) {
            val token = result.googleSignInAccount.idToken
            Toast.makeText(this, "Deu certo!! Token Google: $token", Toast.LENGTH_LONG).show()
            sendToInicioGuia()
        }

        if (result is GoogleLogInActivityContract.Result.Error) {
            Toast.makeText(this, "Deu erro ao fazer login com o Google", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private val permissions = listOf("public_profile", "email")
    }

    private fun sendToInicioGuia() {
        Handler().postDelayed({
            val intent = Intent(this, InicioGuia::class.java)
            startActivity(intent)
        }, 4000)
    }
}
