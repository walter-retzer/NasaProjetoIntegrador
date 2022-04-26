package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.detalheplaneta.DetalhePlaneta


class FragmentPlanet : Fragment(R.layout.screen_planetas) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_search = view.findViewById<ImageView>(R.id.imagem_planeta4)

        btn_search.setOnClickListener {
            val intent = Intent(activity, DetalhePlaneta::class.java)
            startActivity(intent)
        }
    }
}


class FragmentPlanet1 : Fragment(R.layout.screen_planetas2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sistemaSolar = view.findViewById<ShapeableImageView>(R.id.sistema_solar)
        val sistemaSolar3 = view.findViewById<ShapeableImageView>(R.id.sistema_solar3)

        Glide.with(this)
            .asGif()
            .load("https://thumbs.gfycat.com/HomelyDesertedEnglishsetter-size_restricted.gif")
            .into(sistemaSolar)

        Glide.with(this)
            .asGif()
            .load("https://i.pinimg.com/originals/82/90/2d/82902d05ea296646ce1cb10f3f291580.gif")
            .into(sistemaSolar3)
    }

//    .load("https://64.media.tumblr.com/5a964c38fd6d9e58d2d6579ec410237e/tumblr_n5jzxlYODV1sp6e2vo1_r1_400.gifv")
//    .load("https://acegif.com/wp-content/uploads/solar-system-13.gif")
//    .load("https://thumbs.gfycat.com/HomelyDesertedEnglishsetter-size_restricted.gif")
//    .load("https://upload.wikimedia.org/wikipedia/commons/1/12/Rotating_earth_axial_tiles_to_orbit.gif")
//    .load("https://i.kym-cdn.com/photos/images/original/000/522/828/d9a.gif")
//    .load("https://www.ufmg.br/espacodoconhecimento/wp-content/uploads/2021/08/7-1.gif")

}


class FragmentPlanet2 : Fragment(R.layout.screen_planetas3)
