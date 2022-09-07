package com.shlyankin.imagesearcher

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.shlyankin.imagesearcher.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class MainScreenImpl : UiScreen<MainScreenImpl>() {
    override val packageName: String
        get() = "com.shlyankin.imagesearcher.ui.MainActivity"
}

fun MainScreen(action: MainScreenImpl.() -> Unit) = MainScreenImpl().invoke(action)

@RunWith(AndroidJUnit4::class)
class LoginTest : TestCase() {


    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginTest() {
        MainScreen {

        }
    }

}