package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.detalheplaneta.DetalhePlaneta

class FragmentPlanet : Fragment(R.layout.screen_planetas){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_search = view.findViewById<ImageView>(R.id.imagem_planeta4)

        btn_search.setOnClickListener {
            val intent = Intent(activity, DetalhePlaneta::class.java)
            startActivity(intent)
        }
    }
}

class FragmentPlanet1 : Fragment(R.layout.screen_planetas2)

class FragmentPlanet2 : Fragment(R.layout.screen_planetas3)
