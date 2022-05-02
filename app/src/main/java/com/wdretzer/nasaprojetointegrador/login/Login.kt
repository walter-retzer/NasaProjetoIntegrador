package com.wdretzer.nasaprojetointegrador.login

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.cadastro.CadastroUsuario
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentCadastro
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia
import com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract
import java.security.MessageDigest


class Login : AppCompatActivity() {

    private val buttonLogin: Button by lazy { findViewById(R.id.btn_login) }
    private val buttonCadastrar: Button by lazy { findViewById(R.id.btn_cadastrar) }
    private val buttonGoogle: Button by lazy { findViewById(R.id.btn_google) }
    private val buttonFacebook: Button by lazy { findViewById(R.id.btn_facebook) }

    private val textEmail: EditText
        get() = findViewById(R.id.input_email_login)

    private val textPassword: EditText
        get() = findViewById(R.id.input_password_login)

    private val progressBar: FrameLayout
        get() = findViewById(R.id.progress_bar_login)

    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics

    val dialogCorrect = DialogFragmentCadastro()

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

        auth = FirebaseAuth.getInstance()
        analytics = Firebase.analytics
        buttonLogin.setOnClickListener { checkDados() }
        buttonCadastrar.setOnClickListener { sendToCadastroUsuario() }
        buttonGoogle.setOnClickListener { googleSignInRequest.launch(googleSignInOptions) }
        buttonFacebook.setOnClickListener { loginFacebook() }
        registerFacebbokCallback()
    }

    private fun checkDados() {

        progressBar.isVisible = true

        if ((textEmail.text?.isEmpty() == true) || (textPassword.text?.isEmpty() == true)) {
            Toast.makeText(this, "Há Campos não Preenchidos!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else if (textPassword.text.length <= 5 && textEmail.text?.isNotEmpty() == true) {
            Toast.makeText(this, "A Senha deve conter 6 Números!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else if ((!textEmail.text.toString().contains("@") ||
                    !textEmail.text.toString().contains(".") ||
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(textEmail.text.toString())
                        .matches())
        ) {
            Toast.makeText(this, "O Email digitado não é válido!", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false

        } else {
            buttonLogin.isVisible = false
            checkLoginUser()
        }
    }


    private fun checkLoginUser() {

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN){
            param(FirebaseAnalytics.Param.METHOD, "login")
        }

        auth.signInWithEmailAndPassword(
            textEmail.text.toString(),
            textPassword.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Autenticando Login...", Toast.LENGTH_LONG).show()
                    Handler().postDelayed({
                        sendToInicioGuia()
                        dialogCorrect.show(supportFragmentManager, dialogCorrect.tag)
                        progressBar.isVisible = false
                        buttonLogin.isVisible = true
                    }, 4000)

                } else {
                    Toast.makeText(this, "Login não realizado! Check seu e-mail e senha!", Toast.LENGTH_LONG).show()
                    progressBar.isVisible = false
                    buttonLogin.isVisible = true
                }
            }
    }


    private fun sendToInicioGuia() {
        Handler().postDelayed({
            val intent = Intent(this, InicioGuia::class.java)
            startActivity(intent)
        }, 4000)
    }


    private fun sendToCadastroUsuario() {
        val intent = Intent(this, CadastroUsuario::class.java)
        startActivity(intent)
    }


    private fun keyHashFacebook() {
        val info: PackageInfo = getPackageManager().getPackageInfo(
            "com.wdretzer.nasaprojetointegrador",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }
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

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN){
            param(FirebaseAnalytics.Param.METHOD, "facebook")
        }

        loginManager.logInWithReadPermissions(
            this,
            callbackManager,
            permissions
        )
    }


    private fun loginGoogle(result: GoogleLogInActivityContract.Result) {

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN){
            param(FirebaseAnalytics.Param.METHOD, "google")
        }

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
}
