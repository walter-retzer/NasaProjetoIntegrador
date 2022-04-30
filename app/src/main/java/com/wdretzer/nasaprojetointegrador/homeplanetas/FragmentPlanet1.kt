package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R


class FragmentPlanet1 : Fragment(R.layout.screen_planetas2) {

    private val sistemaSolar: ShapeableImageView?
        get() = view?.findViewById(R.id.sistema_solar)

    private val sistemaSolar2: ShapeableImageView?
        get() = view?.findViewById(R.id.sistema_solar2)

    private val sistemaSolar3: ShapeableImageView?
        get() = view?.findViewById(R.id.sistema_solar3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .asGif()
            .load("https://thumbs.gfycat.com/HomelyDesertedEnglishsetter-size_restricted.gif")
            .into(sistemaSolar!!)

        Glide.with(this)
            .asGif()
            .load("https://i.pinimg.com/originals/82/90/2d/82902d05ea296646ce1cb10f3f291580.gif")
            .into(sistemaSolar3!!)
    }
}
