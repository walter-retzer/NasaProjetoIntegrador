package com.wdretzer.nasaprojetointegrador.homeplanetas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.adapter.AdapterHomePlanets

class HomePlanetas : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy { findViewById(R.id.view_pager_planetas) }
    private val planetas: TextView by lazy { findViewById(R.id.planeta) }
    private val imagem: TextView by lazy { findViewById(R.id.imagem) }
    private val favoritos: TextView by lazy { findViewById(R.id.favoritos) }
    private var setPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_planetas)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        getSupportActionBar()?.hide()

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            setPosition = bundle.getInt("position")
            setPosition(setPosition)
        }

        setupViewPager()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setPosition(position)
                super.onPageSelected(position)
            }
        })

    }

    private fun setupViewPager() {

        val listFragments = listOf(
            FragmentPlanet(),
            FragmentPlanet1(),
            FragmentPlanet2()
        )

        viewPager.adapter = AdapterHomePlanets(this, listFragments)
        viewPager.currentItem = setPosition
    }


    private fun setPosition(position: Int) {
        if (position == 2) {
            planetas.setBackgroundResource(R.drawable.background_item_plhome_black)
            imagem.setBackgroundResource(R.drawable.background_item_plhome_black)
            favoritos.setBackgroundResource(R.drawable.background_item_plhome)

        } else if (position == 1) {
            planetas.setBackgroundResource(R.drawable.background_item_plhome_black)
            imagem.setBackgroundResource(R.drawable.background_item_plhome)
            favoritos.setBackgroundResource(R.drawable.background_item_plhome_black)

        } else if (position == 0) {
            planetas.setBackgroundResource(R.drawable.background_item_plhome)
            imagem.setBackgroundResource(R.drawable.background_item_plhome_black)
            favoritos.setBackgroundResource(R.drawable.background_item_plhome_black)
        }
    }
}
