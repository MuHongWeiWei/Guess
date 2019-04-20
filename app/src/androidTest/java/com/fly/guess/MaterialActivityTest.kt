package com.fly.guess

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {

    @Rule
    @JvmField
    val materialrules = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong() {
        val secret = materialrules.activity.secretNumber.secret
        val resource = materialrules.activity.resources
        for (num in 1..10) {
            if (num != secret) {
                onView(withId(R.id.et_number)).perform(clearText())
                onView(withId(R.id.et_number)).perform(typeText(num.toString()))
                onView(withId(R.id.button)).perform(click())

                val message = if (num < secret) resource.getString(R.string.bigger)
                else resource.getString(R.string.smaller)

                onView(withText(message)).check(matches(ViewMatchers.isDisplayed()))
                onView(withText(resource.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun restart() {
        val resource = materialrules.activity.resources
        onView(withId(R.id.et_number)).perform(typeText("11"))
        onView(withId(R.id.button)).perform(click())
        onView(withText(resource.getString(R.string.ok))).perform(click(), closeSoftKeyboard())
        onView(withId(R.id.fab)).perform(click())
        onView(withText(resource.getString(R.string.ok))).perform(click())
        onView(withId(R.id.count)).check(matches(withText("0")))
    }
}