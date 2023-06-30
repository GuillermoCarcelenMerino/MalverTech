package com.example.marvelapplication.ui

import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvelapplication.ui.characters.fragment.CharactersFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BasicFragmentTest {

    @Test
    fun test_asdasd() {
        val scenario = launchFragment<CharactersFragment>()
    }
}
