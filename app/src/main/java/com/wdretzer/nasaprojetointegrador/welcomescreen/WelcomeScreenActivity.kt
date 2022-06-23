package com.wdretzer.nasaprojetointegrador.welcomescreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.login.Login


class WelcomeScreenActivity : AppCompatActivity(R.layout.activity_splash) {
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.view_pager) }
    private val indicator: TabLayout by lazy { findViewById(R.id.indicator) }
    private val buttonNext: Button by lazy { findViewById(R.id.btn_next) }

    private var click: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Desabilita a Action Bar que exibe o nome do Projeto:
        supportActionBar?.hide()

        setupViewPager()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 2) {
                    buttonNext.setText("ENTENDI")
                } else {
                    buttonNext.setText("PRÓXIMO")
                }
            }
        })

        buttonNext.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
            click++

            if (click == 3 || (click == 1 && viewPager.currentItem == 2)) {
                startActivity(Intent(this, Login::class.java))
                finish()
            }
        }
    }

    private fun setupViewPager() {

        val listFragments = listOf(
            SampleFragment(),
            SampleFragment2(),
            SampleFragment3()
        )

        viewPager.adapter = WelcomeScreenAdapter(
            this, listFragments
        )

        TabLayoutMediator(indicator, viewPager) { _, _ -> }.attach()
    }
}