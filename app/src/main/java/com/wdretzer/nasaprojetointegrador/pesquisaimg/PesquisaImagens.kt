package com.wdretzer.nasaprojetointegrador.pesquisaimg

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.storage.FirebaseStorage
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImgensNasa
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.util.*


class PesquisaImagens : AppCompatActivity() {

    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance

//    private val firebaseDB = FirebaseDatabase.getInstance()
//    private val ref = firebaseDB.getReference("doctors")

    private val buttonPlanetas: Button by lazy { findViewById(R.id.btn_searchImagens) }
    private val animationView: LottieAnimationView by lazy { findViewById(R.id.lottie) }
    private val img: ImageView by lazy { findViewById(R.id.search_imagem) }
    private val textView: TextView by lazy { findViewById(R.id.tituloMenuImagens) }
    private val textSearch: TextInputEditText
        get() = findViewById(R.id.input_search_img)
    private var searchWords: String = ""


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa_imagens)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()
        translateTextSearch()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun translateTextSearch() {
        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.PORTUGUESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()
        val translator = Translation.getClient(translationConfigs)

        buttonPlanetas.setOnClickListener {

            if (textSearch.text?.isEmpty() == true) {
                Toast.makeText(this, "Digite uma palavra!!", Toast.LENGTH_SHORT).show()
            }

            if (textSearch.text?.isNotEmpty() == true) {

                uploadToFirebase(saveFile())

                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pesquisando imagens...", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Error no item pesquisado, digite novamente, por favor.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                translator.translate(textSearch.text.toString())
                    .addOnSuccessListener {
                        textView.setText(it)
                        searchWords = it

                        img.visibility = View.INVISIBLE
                        animationView.isVisible = true
                        animationView.loop(true)
                        animationView.playAnimation()

                        // Iniciando a Tela com as Imagens Pesquisadas:
                        Handler().postDelayed({
                            animationView.pauseAnimation()
                            sendToImagensNasa(searchWords)
                            img.visibility = View.VISIBLE
                        }, 6000)

                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                        Toast.makeText(
                            this,
                            "Tente novamente, falha com a conexão da internet!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }


    private fun sendToImagensNasa(search: String) {
        val intent = Intent(this, ImgensNasa::class.java).apply {
            putExtra("Search", search)
        }
        startActivity(intent)
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun uploadToFirebase(uri: Uri) {

        var nameFile = ""
        try {
            nameFile = sharedPref.readString("Id")
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao ler o Id", Toast.LENGTH_SHORT).show()
        }

        val firebaseStorage = FirebaseStorage.getInstance()
        val storage = firebaseStorage.getReference("Pesquisa")
        val fileReference = storage.child("search_$nameFile.txt")

        uri.apply {
            fileReference
                .putFile(this)
                .addOnSuccessListener {
                    //Toast.makeText(this@PesquisaImagens, "Arquivo Enviado ao Firebase Storage!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    //Toast.makeText(this@PesquisaImagens, "Arquivo Não Enviado ao Firebase Storage!", Toast.LENGTH_SHORT).show()
                }
        }
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveFile(): Uri {
        val file = getDisc()

        if (!file.exists() && !file.mkdirs()) {
            file.mkdir()
        }

        var nameFile = ""
        try {
            nameFile = sharedPref.readString("Id")
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, "Erro ao criar o nome do Arquivo Search!", Toast.LENGTH_SHORT).show()
        }

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy_HH.mm.ss")
        val date = simpleDateFormat.format(Date())
        val name = "search_$nameFile.txt"
        val fileName = file.absolutePath + "/" + name
        val newFile = File(fileName)

        try {

            val fileWriter = FileWriter(newFile, true)
            newFile.appendText("Data: $date; Palavra Pesquisada: " + textSearch.text.toString() + "; \n")
            fileWriter.flush()
            fileWriter.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            //Toast.makeText(this, "Arquivo para o Firebase Storage Inexistente!", Toast.LENGTH_SHORT).show()

        } catch (e: IOException) {
            e.printStackTrace()
            //Toast.makeText(this, "Falha ao Salvar o Arquivo!", Toast.LENGTH_SHORT).show()

        }

        return Uri.parse(newFile.toUri().toString())

    }


    private fun getDisc(): File {
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        return File(file, "NASA_SEARCH")
    }

}
