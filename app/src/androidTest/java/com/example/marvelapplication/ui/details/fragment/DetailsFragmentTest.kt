package com.example.marvelapplication.ui.details.fragment

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvelapplication.model.Thumbnail
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.events.MarvelEvent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMarvelCharacter_renderFragment() { // This test dosnt test the image
        val fragmentArgs = bundleOf()
        fragmentArgs.putParcelable(
            "data",
            MarvelCharacter(
                10,
                "Iron Man",
                Thumbnail(
                    "https://b974887.smushcdn.com/974887/wp-content/uploads/2023/03/imagen",
                    "jpg",
                ),
                "Persona de super heroe encarnado por el billonario Tony Stark.",
            ),
        )
        launchFragmentInContainer<DetailsFragment>(fragmentArgs)
        composeTestRule.onNodeWithText("Iron Man").assert(hasText("Iron Man"))
        composeTestRule.onNodeWithText("Persona de super heroe encarnado por el billonario Tony Stark.")
            .assertExists()
    }

    @Test
    fun testEvent_rendersEndAndStart() { // This test dosnt test the image
        val fragmentArgs = bundleOf()
        fragmentArgs.putParcelable(
            "data",
            MarvelEvent(
                10,
                "Iron Man",
                Thumbnail(
                    "https://b974887.smushcdn.com/974887/wp-content/uploads/2023/03/imagen",
                    "jpg",
                ),
                "Persona de super heroe encarnado por el billonario Tony Stark.",
                start = "10 de abril",
                end = "11 de abril",
            ),
        )
        launchFragmentInContainer<DetailsFragment>(fragmentArgs)
        composeTestRule.onNodeWithText("Iron Man").assert(hasText("Iron Man"))
        composeTestRule.onNodeWithText("Persona de super heroe encarnado por el billonario Tony Stark.")
            .assertExists()
        composeTestRule.onNodeWithText("Start")
            .assertExists()
        composeTestRule.onNodeWithText("End")
            .assertExists()
        composeTestRule.onNodeWithText("10 de abril")
            .assertExists()
        composeTestRule.onNodeWithText("11 de abril")
            .assertExists()
    }
}
