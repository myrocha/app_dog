package br.com.appdog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.appdog.view.activity.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    SharedPreferences preferences;
    Context appContext;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class, false, true);

    @Test
    public void useAppContext() {
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("br.com.appdog", appContext.getPackageName());

    }

    @Before
    public void setUp() {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        preferences = targetContext.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String value = preferences.getString(targetContext.getString(R.string.token), "");
        Assert.assertNotNull(value);
    }

    /**
     * verifies that the screen elements are visible.
     */
    @Test
    public void displayView() {
        onView(withId(R.id.btn_acess)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_edit_email)).check(matches(isDisplayed()));
        onView(withId(R.id.img_login)).check(matches(isDisplayed()));
        onView(withId(R.id.progressbar_login)).check(matches(not(isDisplayed())));


    }

    /**
     * validates if something has been typed in the email field.
     */
    @Test
    public void emailEmpty() {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        onView(withId(R.id.txt_edit_email)).perform(typeText(""));
        onView(withId(R.id.btn_acess)).perform(click());
        String text = targetContext.getString(R.string.empty_field);
        onView(withText(text)).check(matches(isDisplayed()));


    }

    /**
     * verifies that an email is valid.
     */
    @Test
    public void emailIvalid() {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        onView(withId(R.id.txt_edit_email)).perform(typeText("emailinvalido"),
                closeSoftKeyboard());

        onView(withId(R.id.btn_acess)).perform(click());
        SystemClock.sleep(5000);
        String text = targetContext.getString(R.string.empty_invalid);
        onView(withText(text)).check(matches(isDisplayed()));


    }

}