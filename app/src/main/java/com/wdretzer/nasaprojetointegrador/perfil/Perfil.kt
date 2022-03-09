package com.wdretzer.nasaprojetointegrador.perfil

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R

class Perfil : AppCompatActivity() {

    private val buttonLogout: TextView by lazy { findViewById(R.id.btn_logout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonLogout.setOnClickListener {
            Toast.makeText(this, "Seus dados foram apagados!", Toast.LENGTH_LONG).show()
        }

    }


}