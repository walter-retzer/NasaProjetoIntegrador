package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.wdretzer.nasaprojetointegrador.R
import java.util.*


class RoverRequestImagesActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val btnSearch: Button
        get() = findViewById(R.id.btn_img_rovers)

    private val title: TextView
        get() = findViewById(R.id.titulo_rover)

    private val textDateRover: TextView
        get() = findViewById(R.id.datas_rover)

    private val textData: TextView
        get() = findViewById(R.id.data_input)

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
        btnSearch.setOnClickListener {
            if (dataSend != " ") sendToRoverSearchActivity()
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
            textDateRover.text = "Imagens disponiveis de $firstDateText a $lastDateText"

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
}
