package com.wdretzer.nasaprojetointegrador.roversearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.RoverLatestImages
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.roverimagens.ImagensRoverAdpter
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class RoverSearchActivity : AppCompatActivity() {

    private val viewModelNasa: NasaViewModel by viewModels()
    private val totalItens: TextView by lazy { findViewById(R.id.text_img_rovers_found) }
    private val loading: FrameLayout
        get() = findViewById(R.id.loading_rover)
    private val recycler: RecyclerView
        get() = findViewById(R.id.rover_recycler)
    private var adp = ImagensRoverAdpter()
    var nextPage: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rover_search)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        chamadas()
    }


    private fun chamadas() {
        viewModelNasa.requestLatestImagesSpirit().observe(this, ::oberservImagesRovers)

        viewModelNasa.requestMissionPerseverance().observe(this) {
            when (it) {
                is DataResult.Loading -> {
                    loading.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    Toast.makeText(this, "Funionou ${it.dataResult.rover}", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModelNasa.requestMissionCuriosity().observe(this) {
            when (it) {
                is DataResult.Loading -> {
                    loading.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(this, "Falha em encontrar as imagens c!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(this, "Retorno Vazio c!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    Toast.makeText(this, "Funionou c ${it.dataResult.rover}", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModelNasa.requestMissionOpportunity().observe(this) {
            when (it) {
                is DataResult.Loading -> {
                    loading.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(this, "Falha em encontrar as imagens o!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(this, "Retorno Vazio o!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    Toast.makeText(this, "Funionou o ${it.dataResult.rover}", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModelNasa.requestMissionSpirit().observe(this) {
            when (it) {
                is DataResult.Loading -> {
                    loading.isVisible = it.isLoading
                }

                is DataResult.Error -> {
                    Toast.makeText(this, "Falha em encontrar as imagens s!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Empty -> {
                    Toast.makeText(this, "Retorno Vazio s!", Toast.LENGTH_LONG).show()
                }

                is DataResult.Success -> {
                    Toast.makeText(this, "Funionou s ${it.dataResult.rover}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    fun oberservImagesRovers(result: DataResult<RoverLatestImages>) {

        when (result) {
            is DataResult.Loading -> {
                loading.isVisible = result.isLoading
            }

            is DataResult.Error -> {
                Toast.makeText(this, "Falha em encontrar as imagens!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Empty -> {
                Toast.makeText(this, "Retorno Vazio!", Toast.LENGTH_LONG).show()
            }

            is DataResult.Success -> {
                adp.updateList(result.dataResult.latest_photos)
                recycler.adapter = adp
                Log.d("Lista:", "${result.dataResult.latest_photos}")
                totalItens.text = "${result.dataResult.latest_photos.size} Imagens Encontradas!"
            }
        }
    }
}
