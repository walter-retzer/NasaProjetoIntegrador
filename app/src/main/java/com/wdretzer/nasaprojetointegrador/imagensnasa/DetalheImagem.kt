package com.wdretzer.nasaprojetointegrador.imagensnasa


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens


class DetalheImagem : AppCompatActivity() {

    var strTranslate: String = ""
    private val buttonMenuPlanets: ImageView by lazy { findViewById(R.id.menu_detalhe_img) }
    private val buttonPesquisaImagem: ImageView by lazy { findViewById(R.id.pesquisa_detalhe_img) }
    private val buttonShareImage: ImageView by lazy { findViewById(R.id.compartilhar_detalhe_img) }
    private val imagemDetalhe: ImageView by lazy { findViewById(R.id.img_detalhe_imagem) }
    private val textoDetalhe: TextView by lazy { findViewById(R.id.descricao_detalhe_img) }
    private val dataDetalhe: TextView by lazy { findViewById(R.id.data_detalhe_img) }
    private val criadoresDetalhe: TextView by lazy { findViewById(R.id.criador_detalhe_img) }
    private val keywordsDetalhe: TextView by lazy { findViewById(R.id.keyword_detalhe_img) }
    private var setSearch: String = ""
    private var setTitle: String = ""
    private var setImg: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_imagem)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        keywordsDetalhe.visibility = View.GONE
        criadoresDetalhe.visibility = View.GONE

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val setText = bundle.getString("Detalhe")
            val setImagem = bundle.getString("Imagem")
            val setDate = bundle.getString("Date")
            val setCriador = bundle.getString("Criador")
            val setKeywords = bundle.getString("Keyword")
            setSearch = bundle.getString("Search").toString()

            setText?.let {
                translate(it, "Detalhe")
                setTitle = it
            }

            setKeywords?.let {
                keywordsDetalhe.visibility = View.VISIBLE
                translate(it, "Keyword")
            }

            setDate?.let {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val formatter = SimpleDateFormat("dd.MM.yyyy,  HH:mm:ss")
                val formattedDate = formatter.format(parser.parse(it))
                dataDetalhe.text = "Data: $formattedDate"
            }

            setCriador?.let {
                criadoresDetalhe.visibility = View.VISIBLE
                criadoresDetalhe.text = "Origem: $it"
            }

            setImagem?.let {

                setImg = it

                Glide.with(imagemDetalhe.context)
                    .load(it)
                    .error(R.drawable.icon_error)
                    .into(imagemDetalhe)

            }
        }

        buttonMenuPlanets.setOnClickListener { sendToHomePlanets() }
        buttonPesquisaImagem.setOnClickListener { sendToSearchImage() }
        buttonShareImage.setOnClickListener { shareImage() }
    }


    private fun translate(str: String, type: String) {
        val translationConfigs = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.PORTUGUESE)
            .build()
        val translator = Translation.getClient(translationConfigs)

        translator.downloadModelIfNeeded()
            .addOnFailureListener {
                Toast.makeText(this, "Erro no Texto da Descrição", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                it.printStackTrace()
            }

        translator.translate(str)
            .addOnSuccessListener {
                if (type == "Detalhe") {
                    textoDetalhe.text = "Descrição: $it"
                }
                if (type == "Keyword") {
                    keywordsDetalhe.text = "Plavras-Chaves: $it"
                }
            }
    }

    private fun sendToHomePlanets() {
        val intent = Intent(this, InicioGuia::class.java)
        startActivity(intent)
    }

    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }

    private fun shareImage() {
        val bitmapDrawable = imagemDetalhe.drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap
        val bitMapPath =
            MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Imagem", null)
        val bitMapUri = Uri.parse(bitMapPath)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, bitMapUri)
        startActivity(Intent.createChooser(intent, "Enviar Imagem!"))
    }
}
