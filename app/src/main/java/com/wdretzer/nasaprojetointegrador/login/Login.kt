package com.wdretzer.nasaprojetointegrador.login

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.cadastro.CadastroUsuario
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentCadastro
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentCadastro.Companion.TAG
import com.wdretzer.nasaprojetointegrador.dialogfragments.ForgotPasswordDialogFragment
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.util.GoogleLogInActivityContract
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa
import org.json.JSONObject
import java.security.MessageDigest


class Login : AppCompatActivity() {

    private val buttonLogin: Button by lazy { findViewById(R.id.btn_login) }
    private val buttonCadastrar: Button by lazy { findViewById(R.id.btn_cadastrar) }
    private val buttonGoogle: Button by lazy { findViewById(R.id.btn_google) }
    private val buttonFacebook: Button by lazy { findViewById(R.id.btn_facebook) }

    private val textEmail: EditText
        get() = findViewById(R.id.input_email_login)

    private val forgotPassword: TextView
        get() = findViewById(R.id.texto_reset_password)

    private val textPassword: EditText
        get() = findViewById(R.id.input_password_login)

    private val progressBar: FrameLayout
        get() = findViewById(R.id.progress_bar_login)

    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics

    val dialogCorrect = DialogFragmentCadastro()
    val dialogForgetPassword = ForgotPasswordDialogFragment()
    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance

    private val loginManager = LoginManager.getInstance()
    private val callbackManager = CallbackManager.Factory.create()
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        analytics = Firebase.analytics
        buttonLogin.setOnClickListener { checkDados() }
        buttonCadastrar.setOnClickListener { sendToCadastroUsuario() }
        buttonGoogle.setOnClickListener { googleSignInRequest.launch(googleSignInOptions) }
        buttonFacebook.setOnClickListener { loginFacebook() }
        forgotPassword.setOnClickListener {
            dialogForgetPassword.show(
                supportFragmentManager,
                dialogForgetPassword.tag
            )
        }
        registerFacebookCallback()
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
            checkLoginUser()
        }
    }


    private fun checkLoginUser() {

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param(FirebaseAnalytics.Param.METHOD, "login")
        }

        auth.signInWithEmailAndPassword(
            textEmail.text.toString(),
            textPassword.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(this, "Autenticando Login...", Toast.LENGTH_LONG).show()

                    if (auth.currentUser != null) {
                        saveNamePerfil("${auth.currentUser?.displayName}")
                        saveId("firebase_${auth.currentUser?.uid}")
                    }

                    Handler().postDelayed({
                        sendToInicioGuia()
                        dialogCorrect.show(supportFragmentManager, dialogCorrect.tag)
                        progressBar.isVisible = false
                        buttonLogin.isVisible = true
                    }, 4000)

                } else {
                    Toast.makeText(
                        this,
                        "Login não realizado! Check seu e-mail e senha!",
                        Toast.LENGTH_LONG
                    ).show()
                    progressBar.isVisible = false
                    buttonLogin.isVisible = true
                }
            }
    }


    // Função para conseguir os dados de nome, email, data de aniversário, gênero.
    private fun getFacebookData(jsonObject: JSONObject?) {
        val name = jsonObject?.getString("name").toString()
        val birthday = jsonObject?.getString("birthday").toString()
        val gender = jsonObject?.getString("gender").toString()
        val email = jsonObject?.getString("email").toString()

        saveNamePerfil(name)
    }


    private fun registerFacebookCallback() {

        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                Toast.makeText(this@Login, "Login Cancelled", Toast.LENGTH_LONG).show()
                progressBar.isVisible = false
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(
                    this@Login,
                    "Deu erro ao fazer login com o Facebook!!",
                    Toast.LENGTH_LONG
                ).show()
                progressBar.isVisible = false
            }

            override fun onSuccess(result: LoginResult) {

                val token = result.accessToken.token
                saveId("facebook_${result.accessToken.userId}")

                val graphRequest =
                    GraphRequest.newMeRequest(result.accessToken) { `object`, response ->
                        getFacebookData(`object`)
                    }
                val parameters = Bundle()
                parameters.putString("fields", "id,email,birthday,gender,name")
                graphRequest.parameters = parameters
                graphRequest.executeAsync()

                val credential = FacebookAuthProvider.getCredential(token)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success")
//                            Toast.makeText(
//                                baseContext, "Autenticando Login com Facebook!",
//                                Toast.LENGTH_SHORT
//                            ).show()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Falha ao Logar com Facebook!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                progressBar.isVisible = false
                dialogCorrect.show(supportFragmentManager, dialogCorrect.tag)
                Handler().postDelayed({
                    sendToInicioGuia()
                }, 2000)

            }
        })
    }


    private fun loginFacebook() {

        progressBar.isVisible = true

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param(FirebaseAnalytics.Param.METHOD, "facebook")
        }

        loginManager.logInWithReadPermissions(
            this,
            callbackManager,
            permissions
        )
    }


    private fun loginGoogle(result: GoogleLogInActivityContract.Result) {

        progressBar.isVisible = true

        analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param(FirebaseAnalytics.Param.METHOD, "google")
        }

        if (result is GoogleLogInActivityContract.Result.Success) {
            val token = result.googleSignInAccount.idToken
            val nome = result.googleSignInAccount.displayName

            saveNamePerfil("$nome")
            saveId("google_${result.googleSignInAccount.id.toString()}")

            val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
            auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        // Toast.makeText(this, "signInWithCredential:success", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "signInWithCredential:success")


                    } else {
                        // If sign in fails, display a message to the user.
                        // Toast.makeText(this, "signInWithCredential:failure", Toast.LENGTH_LONG).show()
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                    }
                }


            //Toast.makeText(this, "Deu certo!! Token Google: $token", Toast.LENGTH_LONG).show()
            progressBar.isVisible = false
            dialogCorrect.show(supportFragmentManager, dialogCorrect.tag)
            Handler().postDelayed({
                sendToInicioGuia()
            }, 2000)

        }

        if (result is GoogleLogInActivityContract.Result.Error) {
            progressBar.isVisible = false
            Toast.makeText(this, "Deu erro ao fazer login com o Google", Toast.LENGTH_LONG).show()
        }
    }


    fun saveId(id: String) {
        sharedPref.saveString("Id", id)
    }


    fun saveNamePerfil(name: String) {
        sharedPref.saveString("Astronauta", name)
    }


    private fun sendToInicioGuia() {
        Handler().postDelayed({
            val intent = Intent(this, MenuPrincipalActivity::class.java)
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


    companion object {
        private val permissions = listOf("email", "public_profile", "user_gender", "user_birthday")
    }
}
