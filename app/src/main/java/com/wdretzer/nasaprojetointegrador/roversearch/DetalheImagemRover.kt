package com.wdretzer.nasaprojetointegrador.roversearch

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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import com.wdretzer.nasaprojetointegrador.pesquisaimg.PesquisaImagens
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class DetalheImagemRover : AppCompatActivity() {

    private val animationView: LottieAnimationView by lazy { findViewById(R.id.lottie) }
    private val imagemDetalhe: ShapeableImageView by lazy { findViewById(R.id.img_detalhe_rover) }
    private val textoDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_titulo) }
    private val roverDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_nome) }
    private val cameraDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_camera) }
    private val dataDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_date) }
    private val solDetalhe: TextView by lazy { findViewById(R.id.detalhe_rover_sol) }
    private val buttonShareImage: ImageView by lazy { findViewById(R.id.compartilhar_detalhe_img) }
    private val buttonSaveImage: ImageView by lazy { findViewById(R.id.salvar_detalhe_img) }
    private val buttonMenuPlanets: ImageView by lazy { findViewById(R.id.menu_detalhe_img) }
    private val buttonPesquisaImagem: ImageView by lazy { findViewById(R.id.pesquisa_detalhe_img) }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_imagem_rover)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()
        checkBundle()

        buttonShareImage.setOnClickListener { shareImage() }
        buttonSaveImage.setOnClickListener { saveImage() }
        buttonMenuPlanets.setOnClickListener { sendToHomePlanets() }
        buttonPesquisaImagem.setOnClickListener { sendToSearchImage() }
    }


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun checkBundle() {

        val bundle: Bundle? = intent.extras
        if (bundle != null) {

            val setImagem = bundle.getString("Imagem")
            val setDescription = bundle.getString("Detalhe")
            val setRover = bundle.getString("Rover")
            val setCamera = bundle.getString("Camera")
            val setDate = bundle.getString("Date")
            val setSol = bundle.getString("Sol")

            setImagem?.let {
                Glide.with(this)
                    .load(it)
                    .error(R.drawable.icon_error)
                    .into(imagemDetalhe)
            }

            setDescription?.let {
                textoDetalhe.text = it
            }

            setRover?.let {
                roverDetalhe.text = "Rover: $it"
            }

            setCamera?.let {
                cameraDetalhe.text = "Camera: $it"
            }

            setDate?.let {
                val parser = SimpleDateFormat("yyyy-MM-dd")
                val formatter = SimpleDateFormat("dd.MM.yyyy")
                val formattedDate = formatter.format(parser.parse(it))
                dataDetalhe.text = "Data: $formattedDate"
            }

            setSol?.let {
                solDetalhe.text = "Sol: $it"
            }
        }
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
        val name = "IMG$date.jpg"
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


    private fun sendToHomePlanets() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }


    private fun sendToSearchImage() {
        val intent = Intent(this, PesquisaImagens::class.java)
        startActivity(intent)
    }


}