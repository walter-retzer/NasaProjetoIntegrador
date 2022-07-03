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
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.dialogfragments.DialogFragmentSignOut
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.login.Login
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.roversearch.RoversMissionActivity
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel

class PerfilSettingsActivity : AppCompatActivity() {

    private val viewModelNasa: NasaViewModel by viewModels()
    private val deleteUser: ImageView by lazy { findViewById(R.id.delete_user_conta) }
    private val logoutUser: ImageView by lazy { findViewById(R.id.logout_user) }
    private val deleteFav: ImageView by lazy { findViewById(R.id.delete_fav) }
    private val astronautaNome: TextView by lazy { findViewById(R.id.nome_astronauta) }
    private val avatarImg: ShapeableImageView by lazy { findViewById(R.id.img_settings) }
    private val buttonMenuPlanets: ImageView by lazy { findViewById(R.id.planetas_menu) }
    private val buttonPesquisaImagem: ImageView by lazy { findViewById(R.id.pesquisa_menu) }
    private val buttonRover: ImageView by lazy { findViewById(R.id.menu_rover) }
    private val buttonFav: ImageView by lazy { findViewById(R.id.menu_fav) }
    private lateinit var auth: FirebaseAuth
    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance
    val dialogSignOut = DialogFragmentSignOut()

    private val loading: FrameLayout
        get() = findViewById(R.id.loading_account)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_settings)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        itensSettings()

        deleteUser.setOnClickListener { showDialogDeleteUser() }
        logoutUser.setOnClickListener { showDialogLogoutUser() }
        deleteFav.setOnClickListener { showDialogDeleAllFAv() }

        buttonMenuPlanets.setOnClickListener { sendToHomePlanets() }
        buttonPesquisaImagem.setOnClickListener { sendToSearchImage() }
        buttonRover.setOnClickListener { sendToRovers() }
        buttonFav.setOnClickListener { sendToFav() }

        auth = FirebaseAuth.getInstance()

    }

    private fun itensSettings() {
        try {
            avatarImg.setImageResource(sharedPref.readString("ImgPerfil").toInt())
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao carregar img!", Toast.LENGTH_SHORT).show()
        }

        try {
            astronautaNome.text = sharedPref.readString("Astronauta")
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao carregar nome do Astronaurta!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteAllFav() {
        viewModelNasa.deleteAllBDNasa().observe(this) {
            if (it is DataResult.Success) {
                Toast.makeText(this, "Todos os dados BD Nasa foram deletados!", Toast.LENGTH_SHORT)
                    .show()
            }

            if (it is DataResult.Loading) {
                loading.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Toast.makeText(this, "Erro ao ao deletar dados no BD Nasa!", Toast.LENGTH_LONG)
                    .show()
            }

            if (it is DataResult.Empty) {
                Log.d("RoverSearch:", "Retorno vazio ao deletar dados no BD!")
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelNasa.deleteAllBDFav().observe(this) {
            if (it is DataResult.Success) {
                Toast.makeText(this, "Todos os dados BD FAV foram deletados!", Toast.LENGTH_SHORT)
                    .show()
            }

            if (it is DataResult.Loading) {
                loading.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Toast.makeText(this, "Erro ao ao deletar dados no BD FAV!", Toast.LENGTH_LONG)
                    .show()
            }

            if (it is DataResult.Empty) {
                Log.d("RoverSearch:", "Retorno vazio ao deletar dados no BD!")
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelNasa.deleteAllBDRover().observe(this) {
            if (it is DataResult.Success) {
                Toast.makeText(this, "Todos os dados BD Rover foram deletados!", Toast.LENGTH_SHORT)
                    .show()
            }

            if (it is DataResult.Loading) {
                loading.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Toast.makeText(this, "Erro ao ao deletar dados no BD Rover!", Toast.LENGTH_LONG)
                    .show()
            }

            if (it is DataResult.Empty) {
                Log.d("RoverSearch:", "Retorno vazio ao deletar dados no BD!")
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }
        }
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


    private fun showDialogDeleAllFAv() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.fragment_dialog_delete_user)

        val btnCancelar = dialog.findViewById(R.id.btn_cancelar) as Button
        val btnDelete = dialog.findViewById(R.id.btn_apagar) as Button

        val body = dialog.findViewById(R.id.frag_title) as TextView
        body.text = "Deseja realmente apagar todas as imagens favoritas?"

        btnCancelar.setOnClickListener { dialog.dismiss() }
        btnDelete.setOnClickListener {
            deleteAllFav()
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
            // Apaga todos os dados salvos no Shared Preferences:
            val editor: SharedPreferences.Editor =
                getSharedPreferences("nasa", Context.MODE_PRIVATE).edit()
            editor.clear()
            editor.apply()

            // Para apagar um item específico dentro do Shared Preference:
            // val myPrefs = this.getSharedPreferences("nasa", Context.MODE_PRIVATE)
            // myPrefs.edit().remove("id").apply()

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


    private fun sendToHomePlanets() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }


    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }


    private fun sendToRovers() {
        val intent = Intent(this, RoversMissionActivity::class.java)
        startActivity(intent)
    }


    private fun sendToFav() {
        val intent = Intent(this, ImagemFavoritosActivity::class.java)
        startActivity(intent)
    }

}