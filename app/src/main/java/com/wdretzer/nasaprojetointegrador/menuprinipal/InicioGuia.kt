package com.wdretzer.nasaprojetointegrador.menuprinipal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.messaging.FirebaseMessaging
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.homeplanetas.HomePlanetas
import com.wdretzer.nasaprojetointegrador.login.Login
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens




class InicioGuia : AppCompatActivity() {

    private val buttonPlanetas: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation1) }
    private val buttonPesquisaImg: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation2) }
    private val buttonImgFav: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation3) }
    private val buttonPerfil: RelativeLayout by lazy { findViewById(com.wdretzer.nasaprojetointegrador.R.id.animmation4) }

    private val imagemRover: ShapeableImageView
        get() = findViewById(R.id.imagem_icon_rover)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wdretzer.nasaprojetointegrador.R.layout.activity_inicio_guia)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        getTokenFirebase()

        Glide.with(this)
            .asGif()
            .load("https://i.pinimg.com/originals/97/42/11/974211f04879e1cc45f8beeac40a0d5e.gif")
            .placeholder(R.drawable.icon_rover)
            .error(R.drawable.icon_error)
            .into(imagemRover)

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
