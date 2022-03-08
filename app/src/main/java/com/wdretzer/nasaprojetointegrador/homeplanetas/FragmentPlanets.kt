package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.imagensnasa.ImgensNasa

class FragmentPlanet : Fragment(R.layout.screen_planetas)

class FragmentPlanet1 : Fragment(R.layout.screen_planetas2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_search = view.findViewById<Button>(R.id.btn_search)

        btn_search.setOnClickListener {
            val intent = Intent(activity, ImgensNasa::class.java)
            startActivity(intent)
        }
    }
}

class FragmentPlanet2 : Fragment(R.layout.screen_planetas3)
