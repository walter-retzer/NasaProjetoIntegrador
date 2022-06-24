package com.wdretzer.nasaprojetointegrador.roversearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.homeplanetas.AdapterHomePlanets
import com.wdretzer.nasaprojetointegrador.util.CustomPageTransformer
import com.wdretzer.nasaprojetointegrador.util.HorizontalMarginItemDecoration


class RoversMissionActivity : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy { findViewById(R.id.view_pager_rovers) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rovers_mission)

        supportActionBar?.hide()
        setupViewPager()
    }

    private fun setupViewPager() {

        val listFragments = listOf(
            SpiritMissionFragment(), // para dar o efeito de visualização em loop (ultimo item)
            PerseveranceMissionFragment(),
            CuriosityMissionFragment(),
            OpportunityMissionFragment(),
            SpiritMissionFragment(),
            PerseveranceMissionFragment(), // para dar o efeito de visualização em loop (primeiro item)
        )

        viewPager.adapter = AdapterHomePlanets(this, listFragments)

        // Implementação do Callback para que haja um loop infinito para mostrar as telas do viewpager
        with(viewPager) {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        if (currentItem == 0)
                            setCurrentItem(listFragments.size - 2, false)
                        else if (currentItem == listFragments.size - 1)
                            setCurrentItem(1, false)
                    }
                }
            })
            setCurrentItem(1, false)
        }

        // Customização para que haja um efeito de animação, de modo a ver um pequena área da próxima tela.
        viewPager.addItemDecoration(
            HorizontalMarginItemDecoration(
                this,
                R.dimen.viewpager_current_item_horizontal_margin
            )
        )

        // Customização para alterar o tamanho do card da próxima tela, quando arrastar para ambos os lados
        viewPager.setPageTransformer(CustomPageTransformer(this))
        viewPager.offscreenPageLimit = 1
    }
}
