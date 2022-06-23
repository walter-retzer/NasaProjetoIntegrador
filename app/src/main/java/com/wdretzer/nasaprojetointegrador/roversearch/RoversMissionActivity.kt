package com.wdretzer.nasaprojetointegrador.roversearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.homeplanetas.AdapterHomePlanets

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
            PerseveranceMissionFragment(),
            CuriosityMissionFragment(),
            OpportunityMissionFragment(),
            SpiritMissionFragment()
        )

        viewPager.adapter = AdapterHomePlanets(this, listFragments)
    }
}
