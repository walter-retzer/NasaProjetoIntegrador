package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.favoritos.ImagemFavoritosActivity
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.perfil.PerfilCompleto
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.util.SharedPrefNasa
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.util.*


class RoverRequestImagesActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val btnSearch: Button
        get() = findViewById(R.id.btn_img_rovers)

    private val btnMenu: ImageView
        get() = findViewById(R.id.btn_menu_planetas_rover)

    private val btnSearchImages: ImageView
        get() = findViewById(R.id.btn_pesquisa_img_rover)

    private val btnMenuRover: ImageView
        get() = findViewById(R.id.btn_menu_rover)

    private val btnMenuFav: ImageView
        get() = findViewById(R.id.btn_favoritos_rover)

    private val btnPerfil: ImageView
        get() = findViewById(R.id.btn_perfil_rover)

    private val title: TextView
        get() = findViewById(R.id.titulo_rover)

    private val textDateRover: TextView
        get() = findViewById(R.id.datas_rover)

    private val textData: TextView
        get() = findViewById(R.id.data_input)

    val sharedPref: SharedPrefNasa = SharedPrefNasa.instance

    var firstDay = 0
    var firstMonth = 0
    var firstYear = 0

    var lastDay = 0
    var lastMonth = 0
    var lastYear = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    var lastDateText: String = ""
    var firstDateText: String = ""

    var dataSend: String = " "


    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_request_images)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        checkBundle()

        textData.setOnClickListener { pickDate() }

        btnMenu.setOnClickListener { sendToHomeMenu() }
        btnSearchImages.setOnClickListener { sendToSearchImage() }
        btnMenuRover.setOnClickListener { sendToRovers() }
        btnMenuFav.setOnClickListener { sendToFavoritos() }
        btnPerfil.setOnClickListener { sendToPerfil() }
        btnSearch.setOnClickListener {
            if (dataSend != " "){
                sendToRoverSearchActivity()
                uploadToFirebase(saveFile())
            }
            else Toast.makeText(this, "Selecione uma data válida!", Toast.LENGTH_LONG).show()
        }
    }


    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkBundle() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            lastDateText = bundle.getString("LastDate").toString()
            firstDateText = bundle.getString("FirstDate").toString()
            title.text = bundle.getString("NameRover").toString()
            textDateRover.text =
                "Imagens da Missão Rover ${title.text} disponiveis de $firstDateText a $lastDateText"

            lastDateText.let {
                val parser = SimpleDateFormat("dd.MM.yyyy")
                val formatterYear = SimpleDateFormat("yyyy")
                val formatterMonth = SimpleDateFormat("MM")
                val formatterDay = SimpleDateFormat("dd")

                val formattedYear = formatterYear.format(parser.parse(it))
                val formattedMonth = formatterMonth.format(parser.parse(it))
                val formattedDay = formatterDay.format(parser.parse(it))

                lastYear = formattedYear.toString().toInt()
                lastMonth = formattedMonth.toString().toInt()
                lastDay = formattedDay.toString().toInt()
            }

            firstDateText.let {
                val parser = SimpleDateFormat("dd.MM.yyyy")
                val formatterYear = SimpleDateFormat("yyyy")
                val formatterMonth = SimpleDateFormat("MM")
                val formatterDay = SimpleDateFormat("dd")

                val formattedYear = formatterYear.format(parser.parse(it))
                val formattedMonth = formatterMonth.format(parser.parse(it))
                val formattedDay = formatterDay.format(parser.parse(it))

                firstYear = formattedYear.toString().toInt()
                firstMonth = formattedMonth.toString().toInt()
                firstDay = formattedDay.toString().toInt()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1
        savedYear = year
        textData.text = "$savedDay-$savedMonth-$savedYear"
        dataSend = "$savedYear-$savedMonth-$savedDay"
    }


    private fun pickDate() {
        val lastDate = Calendar.getInstance()
        lastDate.set(lastYear, lastMonth - 1, lastDay)

        val firstDate = Calendar.getInstance()
        firstDate.set(firstYear, firstMonth - 1, firstDay)

        val dialog = DatePickerDialog(this, this, lastYear, lastMonth, lastDay)
        dialog.datePicker.minDate = firstDate.timeInMillis
        dialog.datePicker.maxDate = lastDate.timeInMillis
        dialog.show()
    }

    private fun sendToRoverSearchActivity() {
        val intent = Intent(this, RoverSearchActivity::class.java).apply {
            putExtra("DateSearch", dataSend)
            putExtra("NameRover", title.text.toString())
        }
        startActivity(intent)
    }

    private fun sendToHomeMenu() {
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

    private fun sendToFavoritos() {
        val intent = Intent(this, ImagemFavoritosActivity::class.java)
        startActivity(intent)
    }

    private fun sendToPerfil() {
        val intent = Intent(this, PerfilCompleto::class.java)
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
                    Log.d("Firebase Storage:", "Arquivo Enviado ao Firebase Storage com sucesso!")
                }
                .addOnFailureListener {
                    Log.d("Firebase Storage:", "rquivo Não Enviado ao Firebase Storage!")
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
            Toast.makeText(
                this,
                "Erro ao criar o nome do Arquivo Search!",
                Toast.LENGTH_SHORT
            ).show()
        }

        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy_HH.mm.ss")
        val date = simpleDateFormat.format(Date())
        val name = "search_$nameFile.txt"
        val fileName = file.absolutePath + "/" + name
        val newFile = File(fileName)

        try {

            val fileWriter = FileWriter(newFile, true)
            newFile.appendText("Data: $date; Rover: ${title.text} , Data Pesquisada: ${textData.text} " + "; \n")
            fileWriter.flush()
            fileWriter.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Log.d("Firebase Storage:", "Arquivo para o Firebase Storage Inexistente!")

        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("Firebase Storage:", "Falha ao Salvar o Arquivo!")
        }

        return Uri.parse(newFile.toUri().toString())
    }


    private fun getDisc(): File {
        return File(this.externalCacheDir!!.absolutePath, "/Nasa")
    }
}
