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


class OpportunityMissionFragment : Fragment(R.layout.fragment_rover_mission) {

    private val viewModelRover: NasaViewModel by viewModels()

    private val loading: FrameLayout?
        get() = view?.findViewById(R.id.loading_rover)

    private val rover: CardView?
        get() = view?.findViewById(R.id.card_rover)

    private val nameRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.nome_rover)

    private val statusRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.status_rover)

    private val totalFotosRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.total_photos_rover)

    private val solMaxRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.sol_rover)

    private val dataMaxRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.max_data_rover)

    private val dataLancamentoRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.launch_data_rover)

    private val dataPousoRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.laund_data_rover)

    private val camerasRoverOpportunity: TextView?
        get() = view?.findViewById(R.id.cameras_data_rover)

    var lastDate: String = ""
    var firstDate: String = ""
    var nameRover: String = ""


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagem = view.findViewById<ShapeableImageView>(R.id.img_rover)
        imagem?.setImageResource(R.drawable.rover_opportunity1)

        rover?.setOnClickListener { sendToSearchImageRovers() }
        chamadas()
    }

    private fun sendToSearchImageRovers() {
        activity?.let {
            val intent = Intent(it, RoverRequestImagesActivity::class.java).apply {
                putExtra("LastDate", lastDate)
                putExtra("FirstDate", firstDate)
                putExtra("NameRover", nameRover)
            }
            it.startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private fun chamadas() {
        viewModelRover.requestMissionOpportunity().observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Loading -> {
                    loading!!.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(
                        context,
                        "Falha em encontrar os dados da Missão Opportunity.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(context, "Retorno Vazio!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    nameRover = it.dataResult.rover.name
                    lastDate = converterDate(it.dataResult.rover.max_date)
                    firstDate = converterDate(it.dataResult.rover.landing_date)

                    nameRoverOpportunity?.text = "Rover ${it.dataResult.rover.name}"

                    if (it.dataResult.rover.status == "complete")
                        statusRoverOpportunity?.text = "Status: Missão Completada"
                    else statusRoverOpportunity?.text = "Status: Missão Ativa"

                    totalFotosRoverOpportunity?.text =
                        "Total de Fotos: ${it.dataResult.rover.total_photos}"

                    solMaxRoverOpportunity?.text = "Sol: ${it.dataResult.rover.max_sol}"

                    dataMaxRoverOpportunity?.text =
                        "Última data em Operação: " + converterDate(it.dataResult.rover.max_date)

                    dataLancamentoRoverOpportunity?.text =
                        "Data do Lançamento: " + converterDate(it.dataResult.rover.launch_date)

                    dataPousoRoverOpportunity?.text =
                        "Data do Pouso em Marte: " + converterDate(it.dataResult.rover.landing_date)

                    val cameras = it.dataResult.rover.cameras
                    val numberCameras = cameras.map { it.name }
                    camerasRoverOpportunity?.text =
                        "Quantidade de Cameras: ${numberCameras.size - 1}"
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
