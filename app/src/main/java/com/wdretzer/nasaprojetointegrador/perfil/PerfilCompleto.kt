package com.wdretzer.nasaprojetointegrador.perfil

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentSignOut
import com.wdretzer.nasaprojetointegrador.login.Login
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa


class PerfilCompleto : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance
    val dialogSignOut = DialogFragmentSignOut()
    private val buttonEditPerfil: TextView by lazy { findViewById(R.id.btn_edit_perfil) }
    private val astronautaPerfil: TextView by lazy { findViewById(R.id.nome_astronauta) }
    private val avatar: ShapeableImageView by lazy { findViewById(R.id.perfil_imagem_avatar_completo) }
    private val deleteUser: ImageView by lazy { findViewById(R.id.delete_user) }
    private val logoutUser: ImageView by lazy { findViewById(R.id.logout_user) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_completo)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        avatar.setStrokeColorResource(R.color.cinza)
        deleteUser.setOnClickListener { showDialogDeleteUser() }
        logoutUser.setOnClickListener { showDialogLogoutUser() }
        buttonEditPerfil.setOnClickListener { sendToEditPerfil() }

        auth = FirebaseAuth.getInstance()

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

    private fun showDialogDeleteUser() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.fragment_dialog_delete_user)

        val body = dialog.findViewById(R.id.frag_title) as TextView
        body.text = "Deseja realmente apagar os dados da sua conta?"
        val btnApagar = dialog.findViewById(R.id.btn_apagar) as Button
        val btnCancelar = dialog.findViewById(R.id.btn_cancelar) as Button

        btnCancelar.setOnClickListener { dialog.dismiss() }
        btnApagar.setOnClickListener {
            deleteAccount()
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun showDialogLogoutUser() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.fragment_dialog_delete_user)

        val btnCancelar = dialog.findViewById(R.id.btn_cancelar) as Button

        val body = dialog.findViewById(R.id.frag_title) as TextView
        body.text = "Deseja realmente realizar logout da sua conta?"

        val btnLogout = dialog.findViewById(R.id.btn_apagar) as Button
        btnLogout.text = "LOGOUT"

        btnCancelar.setOnClickListener { dialog.dismiss() }
        btnLogout.setOnClickListener {
            logoutAccount()
            dialog.dismiss()
        }
        dialog.show()
    }

    // Deleta todos os dados do Perfil no Shared Preference que foram salvos:
    private fun deleteAccount() {
        try {
            val editor: SharedPreferences.Editor =
                getSharedPreferences("nasa", Context.MODE_PRIVATE).edit()
            editor.clear()
            editor.apply()

        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao deletar o Shared Pref!", Toast.LENGTH_SHORT).show()
            Log.d("Perfil:", "Erro: $e ao deletar os dados do Shared Pref do Perfil!")
        }

        // Deletando o perfil da conta que esta ativa no Firebase Authentication:
        try {
            GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
            LoginManager.getInstance().logOut()

            if (auth.currentUser != null) {
                auth.currentUser!!
                    .delete().addOnCompleteListener { task: Task<Void?> ->
                        if (task.isSuccessful) {
                            Log.d("Firebase Auth:", "Deletion Success")
                            Toast.makeText(
                                this,
                                "Perfil Firebase deletado com Sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Erro ao deletar o Perfil Firebase!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

            Toast.makeText(this, "Perfil deletado com Sucesso", Toast.LENGTH_SHORT).show()

        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao deletar os Dados do Perfil!", Toast.LENGTH_SHORT).show()
            Log.d("Perfil:", "Erro: $e ao deletar os dados do Perfil!")
        }

        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }, 3000)
    }


    private fun logoutAccount() {
        try {
            val editor: SharedPreferences.Editor =
                getSharedPreferences("nasa", Context.MODE_PRIVATE).edit()
            editor.clear()
            editor.apply()

        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao deletar o Shared Pref!", Toast.LENGTH_SHORT).show()
            Log.d("Perfil:", "Erro: $e ao deletar os dados do Shared Pref do Perfil!")
        }

        try {
            GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut()
            LoginManager.getInstance().logOut()

            if (auth.currentUser != null) {
                auth.signOut()
                Toast.makeText(this, "Firebase Logout com Sucesso!", Toast.LENGTH_SHORT).show()
            }

        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro no Firebase Logout!", Toast.LENGTH_SHORT).show()
            Log.d("Perfil:", "Erro: $e no Firebase Logout!")
        }

        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }, 3000)
    }
}
