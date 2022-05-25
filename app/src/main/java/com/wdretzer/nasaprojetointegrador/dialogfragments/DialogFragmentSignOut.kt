package com.wdretzer.nasaprojetointegrador.dialogfragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.login.Login
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa


class DialogFragmentSignOut : DialogFragment() {

    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance
    private val btnSignOut: Button?
        get() = view?.findViewById(R.id.btn_singout)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_sing_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignOut?.setOnClickListener { deletePerfil() }
    }

    private fun deletePerfil() {
        val user = Firebase.auth.currentUser!!
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(DialogFragmentCadastro.TAG, "User account deleted.")
                    Toast.makeText(context, "Conta deletada!", Toast.LENGTH_SHORT).show()
                    saveNamePerfil("Nome do Astronauta")
                    sendToLogin()
                }
            }
    }

    fun saveNamePerfil(name: String) {
        sharedPref.saveString("Astronauta", name)
    }

    private fun sendToLogin() {
        Handler().postDelayed({
            val intent = Intent(context, Login::class.java)
            startActivity(intent)
        }, 3000)
    }

    companion object {
        const val TAG = "CorrectPasswordDialog"
    }
}
