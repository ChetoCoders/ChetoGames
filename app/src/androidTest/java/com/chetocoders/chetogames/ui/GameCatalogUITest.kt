package com.chetocoders.chetogames.ui

import android.Manifest
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.rule.GrantPermissionRule
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.ui.gameCatalog.GameCatalogFragment
import com.chetocoders.chetogames.utils.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class GameCatalogUITest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var runtimePermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToAddGameScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<GameCatalogFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)

            // Set the currentDestination on the TestNavHostController
            navController.setCurrentDestination(R.id.gameCatalogFragment)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(requireView(), navController)
        }

        // Verify bottom nav bar is displayed
        onView(withId(R.id.bottomLayout)).check(matches(isDisplayed()))

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.addGame)).perform(click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.addGameFragment)
    }
}