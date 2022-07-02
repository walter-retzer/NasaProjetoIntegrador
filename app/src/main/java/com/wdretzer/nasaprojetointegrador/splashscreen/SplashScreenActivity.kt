package com.wdretzer.nasaprojetointegrador.splashscreen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa
import com.wdretzer.nasaprojetointegrador.welcomescreen.WelcomeScreenActivity


class SplashScreenActivity : AppCompatActivity(R.layout.activity_start) {

    var mMediaPlayer: MediaPlayer? = null
    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance
    var idLogin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        // Funções para baixar arquivo que executa as traduções de idioma utilizados no App:
        filesForTranslatePtEng()
        filesForTranslateEngPt()

        checkLogin()

        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(
                this,
                R.raw.song_foguete
            )

            mMediaPlayer!!.isLooping = true //to repeat again n again
            mMediaPlayer!!.start() //to start the sound

        } else mMediaPlayer!!.start()

    }


    private fun checkLogin(){
        try {
            idLogin = sharedPref.readString("Id")
            Toast.makeText(this, "Id: $idLogin", Toast.LENGTH_SHORT).show()


            if (sharedPref.readString("Id").isEmpty()) {
                Toast.makeText(this, "Id vazio!!", Toast.LENGTH_SHORT).show()
                // Iniciando as Telas de Boas Vindas:
                Handler().postDelayed({
                    mMediaPlayer!!.stop()
                    val intent = Intent(this, WelcomeScreenActivity::class.java)
                    startActivity(intent)
                }, 10000)

            } else {
                // Iniciando as Telas de Boas Vindas:
                Handler().postDelayed({
                    mMediaPlayer!!.stop()
                    val intent = Intent(this, MenuPrincipalActivity::class.java)
                    startActivity(intent)
                }, 3000)
            }


        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao ler Id!!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBackPressed() {
        val intent = Intent(this, SplashScreenActivity::class.java)
        startActivity(intent)
    }

    private fun filesForTranslatePtEng() {
        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.PORTUGUESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val translator = Translation.getClient(translationConfigs)

        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                Log.d("Tradutor Pt-Eng:", "Arquivos Pt-Eng prontos para uso!")
            }
            .addOnFailureListener {
                Log.d("Tradutor Pt-Eng:", "Tente novamente, falha com a conexão da internet!")
            }

        translator.translate("Astronaut")
            .addOnSuccessListener {
                Log.d("Tradutor Pt-Eng:", "Tradução Pt-Eng pronta para uso!")
            }
            .addOnFailureListener {
                it.printStackTrace()
                Log.d("Tradutor Pt-Eng:", "Baixando arquivos de tradução Pt-Eng.")
            }
    }

    private fun filesForTranslateEngPt() {
        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.PORTUGUESE)
            .build()
        val translator = Translation.getClient(translationConfigs)

        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                Log.d("Tradutor Eng-Pt:", "Arquivos Eng-Pt prontos para uso!")
            }
            .addOnFailureListener {
                Log.d("Tradutor Eng-Pt:", "Tente novamente, falha com a conexão da internet!")
            }

        translator.translate("Astronauta")
            .addOnSuccessListener {
                Log.d("Tradutor Eng-Pt:", "Tradução Eng-Pt pronta para uso!")
            }
            .addOnFailureListener {
                it.printStackTrace()
                Log.d("Tradutor Eng-Pt:", "Baixando arquivos de tradução Eng-Pt.")
            }
    }
}
