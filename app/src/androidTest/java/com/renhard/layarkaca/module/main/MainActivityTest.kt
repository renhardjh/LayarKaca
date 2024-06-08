package com.renhard.layarkaca.module.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.renhard.layarkaca.R
import com.renhard.layarkaca.utils.DataDummy
import com.renhard.layarkaca.utils.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTVShows = DataDummy.generateDummyTVShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun a_loadMovies() {
        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun b_loadTVShow() {
        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvshow))
            .perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVShows.size))
    }

    @Test
    fun c_loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.toolbar_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_rating))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun d_loadDetailTVShow() {
        onView(withId(R.id.tvshow))
            .perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.toolbar_layout))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_rating))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_description))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun e_loadMoviesFavorite() {
        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform(ViewActions.click())
        onView(withId(R.id.rv_movie_favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie_favorite))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun f_loadTVShowFavorite() {
        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform(ViewActions.click())
        val tabView = onView(
            Matchers.allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow_favorite))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun g_addMovieFavorite() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))

        onView(withId(R.id.favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform(ViewActions.click())
        onView(withId(R.id.rv_movie_favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie_favorite))
            .check(RecyclerViewItemCountAssertion(1))
    }

    @Test
    fun h_addTVShowFavorite() {
        onView(withId(R.id.tvshow))
            .perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))

        onView(withId(R.id.favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform(ViewActions.click())
        val tabView = onView(
            Matchers.allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow_favorite))
            .check(RecyclerViewItemCountAssertion(1))
    }

    @Test
    fun i_removeFavoriteMovie() {
        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform(ViewActions.click())
        onView(withId(R.id.rv_movie_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))

        onView(withId(R.id.favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_movie_favorite))
            .check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    fun j_removeFavoriteTVShow() {
        onView(withId(R.id.bottomMenu))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform(ViewActions.click())
        val tabView = onView(
            Matchers.allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))

        onView(withId(R.id.favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.rv_tvshow_favorite))
            .check(RecyclerViewItemCountAssertion(0))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter!!.itemCount, Matchers.equalTo(expectedCount))
        }
    }
}