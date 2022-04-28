package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.detalheplaneta.DetalhePlaneta


class FragmentPlanet : Fragment(R.layout.screen_planetas) {

    private val btnMarte: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta4)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMarte?.setOnClickListener {
            val intent = Intent(activity, DetalhePlaneta::class.java)
            startActivity(intent)
        }
    }
}
