package ch.ny.livios.movielion;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        activityRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void search() {
        onView(withId(R.id.searchview_main_search)).perform(click());
        //onView(withId(R.id.searchview_main_search)).perform(typeText("Avengers: Endgame"));
        onView(isAssignableFrom(EditText.class)).perform(typeText("Avengers: Endgame"), pressImeActionButton());

        onData(anything())
                .inAdapterView(withId(R.id.listview_main_search))
                .atPosition(0)
                .perform(click());
    }
}