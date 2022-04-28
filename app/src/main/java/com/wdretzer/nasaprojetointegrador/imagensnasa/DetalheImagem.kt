package com.wdretzer.nasaprojetointegrador.imagensnasa


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.InicioGuia
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class DetalheImagem : AppCompatActivity() {

    var strTranslate: String = ""
    private val animationView: LottieAnimationView by lazy { findViewById(R.id.lottie) }
    private val buttonMenuPlanets: ImageView by lazy { findViewById(R.id.menu_detalhe_img) }
    private val buttonPesquisaImagem: ImageView by lazy { findViewById(R.id.pesquisa_detalhe_img) }
    private val buttonShareImage: ImageView by lazy { findViewById(R.id.compartilhar_detalhe_img) }
    private val buttonSaveImage: ImageView by lazy { findViewById(R.id.salvar_detalhe_img) }
    private val imagemDetalhe: ImageView by lazy { findViewById(R.id.img_detalhe_imagem) }
    private val textoDetalhe: TextView by lazy { findViewById(R.id.descricao_detalhe_img) }
    private val dataDetalhe: TextView by lazy { findViewById(R.id.data_detalhe_img) }
    private val criadoresDetalhe: TextView by lazy { findViewById(R.id.criador_detalhe_img) }
    private val keywordsDetalhe: TextView by lazy { findViewById(R.id.keyword_detalhe_img) }
    private var setSearch: String = ""
    private var setTitle: String = ""
    private var setImg: String = ""

    @SuppressLint("SimpleDateFormat")
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
        buttonSaveImage.setOnClickListener { saveImage() }
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

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun saveImage() {
        val file = getDisc()

        if (!file.exists() && !file.mkdirs()) {
            file.mkdir()
        }

        val simpleDateFormat = SimpleDateFormat("ddMMyyyyHHmmss")
        val date = simpleDateFormat.format(Date())
        val name = "IMG" + date + ".jpg"
        val fileName = file.absolutePath + "/" + name
        val newFile = File(fileName)

        try {
            val draw = imagemDetalhe.drawable as BitmapDrawable
            val bitmap = draw.bitmap
            val fileOutPutStream = FileOutputStream(newFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutPutStream)

            // Iniciando a Animação de salvar:
            animationView.isVisible = true
            animationView.loop(true)
            animationView.playAnimation()
            Handler().postDelayed({
                animationView.pauseAnimation()
                animationView.isVisible = false
                Toast.makeText(this, "Imagem Salva na Galeria!", Toast.LENGTH_SHORT).show()
            }, 3000)

            fileOutPutStream.flush()
            fileOutPutStream.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this, "Arquivo Inexistente!", Toast.LENGTH_SHORT).show()

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Falha ao Salvar a Imagem!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getDisc(): File {
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File(file, "NASA_APP_IMAGES")
    }
}
