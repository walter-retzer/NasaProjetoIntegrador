package com.wdretzer.nasaprojetointegrador.favoritos

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.FavouritesItens
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImagensNasa
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import com.wdretzer.nasaprojetointegrador.roversearch.RoversMissionActivity
import com.wdretzer.nasaprojetointegrador.viewmodel.NasaViewModel


class ImagemFavoritosActivity : AppCompatActivity() {

    private val viewModelNasa: NasaViewModel by viewModels()
    private val buttonHomePlanets: ImageView by lazy { findViewById(R.id.inicio_fav) }
    private val buttonPesquisaImagens: ImageView by lazy { findViewById(R.id.pesquisar_img_fav) }
    private val buttonMenuRovers: ImageView by lazy { findViewById(R.id.rover_fav) }
    private val buttonDeleteFav: ImageView by lazy { findViewById(R.id.delete_all_fav) }

    private val loading: FrameLayout?
        get() = findViewById(R.id.loading)

    private val recycler: RecyclerView
        get() = findViewById(R.id.nasa_recycler_fav)

    private var adp = ItensFavoritosAdapter(::removeFavourite)

    private var setSearch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_favoritos)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adp

        showFavourite()
        buttonPesquisaImagens.setOnClickListener { sendToSearchImage() }
        buttonHomePlanets.setOnClickListener { sendToHomePlanets() }
        buttonDeleteFav.setOnClickListener { showDialogDeleAllFAv() }
        buttonMenuRovers.setOnClickListener { sendToRovers() }
    }

    override fun onBackPressed() {

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setSearch = bundle.getString("Search").toString()
        }

        if (setSearch != "") {
            val intent = Intent(this, ImagensNasa::class.java).apply {
                putExtra("Search", setSearch)
            }
            startActivity(intent)
        } else {
            val intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showFavourite() {
        viewModelNasa.getFavouriteImages().observe(this) {

            if (it is DataResult.Loading) {
                loading?.isVisible = it.isLoading
            }

            if (it is DataResult.Success) {
                Log.d("BD_FAV:", "Lista: ${it.dataResult}")
                adp.updateList(it.dataResult)

                if (it.dataResult.size == 0) {
                    Toast.makeText(
                        this,
                        "Ainda não há imagens favoritas salvas!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            if (it is DataResult.Error) {
                Toast.makeText(
                    this,
                    "Erro ao acessar as imagens favoritas salvas.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun removeFavourite(item: FavouritesItens) {
        // deletar o item no banco de dados Nasa!!
        viewModelNasa.removeFavouriteImg(item).observe(this) {
            if (it is DataResult.Loading) {
                loading!!.isVisible = it.isLoading
            }

            if (it is DataResult.Success) {
                adp.updateItem(it.dataResult)
            }

            if (it is DataResult.Error) {
                Log.d("BD FavImgNasa:", "Erro ${it.error} ao remover item ${item.img} do BD!")
                Toast.makeText(this, "Erro ao deletar item!", Toast.LENGTH_LONG).show()
            }

            if (it is DataResult.Empty) {
                Log.d("BD FavImg:", "Retorno vazio ao remover item ${item.img} do BD!")
            }
        }

        // deletar a img no banco de Dados Fav:
        viewModelNasa.addOrRemoveFavouriteImg(item).observe(this) {
            if (it is DataResult.Success) {
                Log.d("BD FavImg:", "Item Removido com Sucesso do BD FavImg!")
            }

            if (it is DataResult.Loading) {
                loading!!.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Log.d("BD FavImg:", "Erro ${it.error} ao remover item ${item.img} do BD!")
                Toast.makeText(this, "Erro ao deletar item!", Toast.LENGTH_LONG).show()
            }

            if (it is DataResult.Empty) {
                Log.d("BD FavImg:", "Retorno vazio ao remover item ${item.img} do BD!")
            }
        }


        // deletar a img no banco de Dados Rover:
        viewModelNasa.removeFavouriteImgRover(item.img).observe(this) {
            if (it is DataResult.Success) {
                Log.d("BD FavRoverImg:", "Item Removido com Sucesso do BD FavRoverImg!")
            }

            if (it is DataResult.Loading) {
                loading!!.isVisible = it.isLoading
            }

            if (it is DataResult.Error) {
                Log.d("BD FavRoverImg:", "Erro ${it.error} ao remover item ${item.img} do BD!")
                Toast.makeText(this, "Erro ao deletar item!", Toast.LENGTH_LONG).show()
            }

            if (it is DataResult.Empty) {
                Log.d("BD FavImg:", "Retorno vazio ao remover item ${item.img} do BD!")
            }
        }
    }


    private fun deleteAllFav() {

        viewModelNasa.deleteAllBDNasa().observe(this) {
            if (it is DataResult.Success) {
                Toast.makeText(this, "Todos os dados BD Nasa foram deletados!", Toast.LENGTH_SHORT)
                    .show()
                this.recreate()
            }

            if (it is DataResult.Loading) {
                loading?.isVisible = it.isLoading
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
                this.recreate()
            }

            if (it is DataResult.Loading) {
                loading?.isVisible = it.isLoading
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
                this.recreate()
            }

            if (it is DataResult.Loading) {
                loading?.isVisible = it.isLoading
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

    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }

    private fun sendToHomePlanets() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun sendToRovers() {
        val intent = Intent(this, RoversMissionActivity::class.java)
        startActivity(intent)
    }
}
