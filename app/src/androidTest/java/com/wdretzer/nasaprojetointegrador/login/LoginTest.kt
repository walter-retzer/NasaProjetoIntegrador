package com.wdretzer.nasaprojetointegrador.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.menuprinipal.MenuPrincipalActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginTest {

    @get:Rule
    val rule = activityScenarioRule<Login>()


    @Before
    fun before() {
        Intents.init()
    }

    @Test
    fun loginWithSuccess() {
        // Entrada
        onView(withId(R.id.input_email_login)).perform(typeText("walter_retzer@yahoo.com.br"))
        onView(withId(R.id.input_password_login)).perform(typeText("123456"))

        // Ação
        onView(withId(R.id.btn_login)).perform(click())

        // Resultado
        Thread.sleep(4000)

        // Será exibido um dialog na tela quando o login tiver tido sucesso:
        onView(withId(R.id.dialog))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))

        Thread.sleep(4000)

        // Iniciará a Activity do Menu Principal:
        InstrumentationRegistry.getInstrumentation().waitForIdle {
            Intents.intended(hasComponent(MenuPrincipalActivity::class.java.name))
        }
    }

    @After
    fun after() {
        Intents.release()
    }
}
