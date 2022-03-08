package com.wdretzer.nasaprojetointegrador.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.homeplanetas.HomePlanetas

class Login : AppCompatActivity() {

    private val buttonLogin: Button by lazy { findViewById(R.id.btn_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonLogin.setOnClickListener {
            val position: Int = 0
            val intent = Intent(this, HomePlanetas::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }
}
