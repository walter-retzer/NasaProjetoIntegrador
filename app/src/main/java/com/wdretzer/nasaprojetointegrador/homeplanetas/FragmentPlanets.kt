package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.detalheplaneta.*


class FragmentPlanet : Fragment(R.layout.screen_planetas) {

    private val btnMercurio: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta1)

    private val btnVenus: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta2)

    private val btnTerra: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta3)

    private val btnMarte: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta4)

    private val btnJupiter: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta5)

    private val btnSaturno: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta6)

    private val btnUrano: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta7)

    private val btnNetuno: ImageView?
        get() = view?.findViewById(R.id.imagem_planeta8)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnMercurio?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaMercurio::class.java)
            startActivity(intent)
        }

        btnVenus?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaVenus::class.java)
            startActivity(intent)
        }

        btnTerra?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaTerra::class.java)
            startActivity(intent)
        }

        btnMarte?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaMarte::class.java)
            startActivity(intent)
        }

        btnSaturno?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaSaturno::class.java)
            startActivity(intent)
        }

        btnJupiter?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaJupiter::class.java)
            startActivity(intent)
        }

        btnUrano?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaUrano::class.java)
            startActivity(intent)
        }

        btnNetuno?.setOnClickListener {
            val intent = Intent(activity, DetalhePlanetaNetuno::class.java)
            startActivity(intent)
        }
    }
}
