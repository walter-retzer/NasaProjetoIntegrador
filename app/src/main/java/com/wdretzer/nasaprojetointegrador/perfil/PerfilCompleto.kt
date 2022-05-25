package com.wdretzer.nasaprojetointegrador.perfil

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentSignOut
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa


class PerfilCompleto : AppCompatActivity() {

    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance
    val dialogSignOut = DialogFragmentSignOut()
    private val buttonEditPerfil: TextView by lazy { findViewById(R.id.btn_edit_perfil) }
    private val astronautaPerfil: TextView by lazy { findViewById(R.id.nome_astronauta) }
    private val avatar: ShapeableImageView by lazy { findViewById(R.id.perfil_imagem_avatar_completo) }
    private val deleteUser: ImageView by lazy { findViewById(R.id.delete_user) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_completo)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        avatar.setStrokeColorResource(R.color.cinza)

        deleteUser.setOnClickListener {
            dialogSignOut.show(
                supportFragmentManager,
                dialogSignOut.tag
            )
        }

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
        val intent = Intent(this, InicioGuia::class.java)
        startActivity(intent)
    }

    private fun sendToEditPerfil() {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }

}
