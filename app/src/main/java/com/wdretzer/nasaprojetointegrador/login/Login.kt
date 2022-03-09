package com.wdretzer.nasaprojetointegrador.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.cadastro.CadastroUsuario
import com.wdretzer.nasaprojetointegrador.cadastro.CadastroUsuarioFacebook
import com.wdretzer.nasaprojetointegrador.cadastro.CadastroUsuarioGoogle
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia

class Login : AppCompatActivity() {

    private val buttonLogin: Button by lazy { findViewById(R.id.btn_login) }
    private val buttonCadastrar: Button by lazy { findViewById(R.id.btn_cadastrar) }
    private val buttonGoogle: Button by lazy { findViewById(R.id.btn_google) }
    private val buttonFacebook: Button by lazy { findViewById(R.id.btn_facebook) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        buttonLogin.setOnClickListener {
            val position: Int = 0
            val intent = Intent(this, InicioGuia::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        buttonCadastrar.setOnClickListener {
            val position: Int = 0
            val intent = Intent(this, CadastroUsuario::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        buttonGoogle.setOnClickListener {
            val position: Int = 0
            val intent = Intent(this, CadastroUsuarioGoogle::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        buttonFacebook.setOnClickListener {
            val position: Int = 0
            val intent = Intent(this, CadastroUsuarioFacebook::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

    }
}
