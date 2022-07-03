package com.wdretzer.nasaprojetointegrador.perfil

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentSignOut
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa


class PerfilCompleto : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance
    val dialogSignOut = DialogFragmentSignOut()
    private val buttonEditPerfil: TextView by lazy { findViewById(R.id.btn_edit_perfil) }
    private val astronautaPerfil: TextView by lazy { findViewById(R.id.nome_astronauta) }
    private val avatar: ShapeableImageView by lazy { findViewById(R.id.perfil_imagem_avatar_completo) }
    private val settingsUser: ImageView by lazy { findViewById(R.id.settings_user) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_completo)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        avatar.setStrokeColorResource(R.color.cinza)

        settingsUser.setOnClickListener { sendToEditPerfilSettings() }
        buttonEditPerfil.setOnClickListener { sendToEditPerfil() }

        try {
            avatar.setImageResource(sharedPref.readString("ImgPerfil").toInt())
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Edite a Foto do seu Perfil!", Toast.LENGTH_SHORT).show()
        }

        try {
            astronautaPerfil.text = sharedPref.readString("Astronauta")
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Edite o Nome do seu Astronauta!", Toast.LENGTH_SHORT).show()
        }

        if (sharedPref.readString("Astronauta").isEmpty()) {
            astronautaPerfil.text = "Nome do Astronauta"
        }
    }


    override fun onBackPressed() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }


    private fun sendToEditPerfil() {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }


    private fun sendToEditPerfilSettings() {
        val intent = Intent(this, PerfilSettingsActivity::class.java)
        startActivity(intent)
    }

}
