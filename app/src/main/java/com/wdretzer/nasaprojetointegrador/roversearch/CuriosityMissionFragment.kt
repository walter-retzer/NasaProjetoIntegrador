package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class CuriosityMissionFragment : Fragment(R.layout.fragment_rover_mission) {

    private val viewModelRover: NasaViewModel by viewModels()

    private val loading: FrameLayout?
        get() = view?.findViewById(R.id.loading_rover)

    private val rover: CardView?
        get() = view?.findViewById(R.id.card_rover)

    private val nameRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.nome_rover)

    private val statusRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.status_rover)

    private val totalFotosRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.total_photos_rover)

    private val solMaxRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.sol_rover)

    private val dataMaxRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.max_data_rover)

    private val dataLancamentoRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.launch_data_rover)

    private val dataPousoRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.laund_data_rover)

    private val camerasRoverCuriosity: TextView?
        get() = view?.findViewById(R.id.cameras_data_rover)

    var lastDate: String = ""
    var firstDate: String = ""
    var nameRover: String = ""
    var update: Boolean = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagem = view.findViewById<ShapeableImageView>(R.id.img_rover)
        imagem?.setImageResource(R.drawable.curiosity01)

        rover?.setOnClickListener { sendToSearchImageRovers() }
        chamadas()
    }

    private fun sendToSearchImageRovers() {
        if (update){
            activity?.let {
                val intent = Intent(it, RoverRequestImagesActivity::class.java).apply {
                    putExtra("LastDate", lastDate)
                    putExtra("FirstDate", firstDate)
                    putExtra("NameRover", nameRover)
                }
                it.startActivity(intent)
            }
        } else{
            Toast.makeText(
                context,
                "Dados da Miss??o ainda n??o carregados, aguarde!",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private fun chamadas() {
        viewModelRover.requestMissionCuriosity().observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Loading -> {
                    loading!!.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(
                        context,
                        "Falha em encontrar os dados da Miss??o Perseverance!",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(context, "Retorno Vazio!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    update = true
                    nameRover = it.dataResult.rover.name
                    lastDate = converterDate(it.dataResult.rover.max_date)
                    firstDate = converterDate(it.dataResult.rover.landing_date)

                    nameRoverCuriosity?.text = "Nome: Rover ${it.dataResult.rover.name}"

                    if (it.dataResult.rover.status == "complete")
                        statusRoverCuriosity?.text = "Status: Miss??o Completada"
                    else statusRoverCuriosity?.text = "Status: Miss??o Ativa"

                    totalFotosRoverCuriosity?.text =
                        "Total de Fotos: ${it.dataResult.rover.total_photos}"

                    solMaxRoverCuriosity?.text = "Sol: ${it.dataResult.rover.max_sol}"

                    dataMaxRoverCuriosity?.text =
                        "??ltima data em Opera????o: " + converterDate(it.dataResult.rover.max_date)

                    dataLancamentoRoverCuriosity?.text =
                        "Data do Lan??amento: " + converterDate(it.dataResult.rover.launch_date)

                    dataPousoRoverCuriosity?.text =
                        "Data do Pouso em Marte: " + converterDate(it.dataResult.rover.landing_date)

                    val cameras = it.dataResult.rover.cameras
                    val numberCameras = cameras.map { it.name }
                    camerasRoverCuriosity?.text = "Quantidade de Cameras: ${numberCameras.size - 1}"
                }
            }
        }
    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun converterDate(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate = formatter.format(parser.parse(date))
        return formattedDate.toString()
    }
}
