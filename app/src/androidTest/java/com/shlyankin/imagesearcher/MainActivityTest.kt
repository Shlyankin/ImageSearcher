package com.shlyankin.imagesearcher

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.shlyankin.imagesearcher.screens.MainScreen
import com.shlyankin.imagesearcher.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase() {


    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainScreenTest() {
        MainScreen {

        }
    }

}