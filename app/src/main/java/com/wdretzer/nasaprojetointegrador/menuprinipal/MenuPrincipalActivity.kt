package com.wdretzer.nasaprojetointegrador.menuprinipal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.homeplanetas.HomePlanetas
import com.wdretzer.nasaprojetointegrador.login.Login
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.roversearch.RoversMissionActivity


class MenuPrincipalActivity : AppCompatActivity() {

    private val buttonPlanetas: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation1) }
    private val buttonPesquisaImg: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation2) }
    private val buttonImgFav: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation3) }
    private val buttonPerfil: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation4) }
    private val buttonRover: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation5) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wdretzer.nasaprojetointegrador.R.layout.activity_inicio_guia)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        getTokenFirebase()

        buttonPlanetas.setOnClickListener {
            val position: Int = 1
            val intent = Intent(this, HomePlanetas::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        buttonPesquisaImg.setOnClickListener {
            val intent = Intent(this, PesquisaImagens::class.java)
            startActivity(intent)
        }

        buttonRover.setOnClickListener {
            val intent = Intent(this, RoversMissionActivity::class.java)
            startActivity(intent)
        }

        buttonImgFav.setOnClickListener {
            val intent = Intent(this, ImagemFavoritosActivity::class.java)
            startActivity(intent)
        }

        buttonPerfil.setOnClickListener {
            val intent = Intent(this, PerfilCompleto::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun getTokenFirebase(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN FIREBASE", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("TOKEN FIREBASE", token)
            //Toast.makeText(baseContext, "Token Firebase: $token", Toast.LENGTH_SHORT).show()
        })
    }
}
