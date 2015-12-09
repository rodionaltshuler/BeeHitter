package com.ottamotta.beehitter.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.ottamotta.beehitter.swarm.Swarm;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class BeeHitActivityTest {

    @Rule
    public ActivityTestRule<BeeHitActivity> rule =
            new ActivityTestRule<>(BeeHitActivity.class);

    @Test
    public void testListDisplayed() throws Exception {
        onView(withId(android.R.id.list)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyViewNotDisplayed() throws Exception {
        onView(withId(android.R.id.empty)).check(doesNotExist());
    }

    @Test
    public void testListProperItemsCount() {
        RecyclerView recyclerView = (RecyclerView) rule.getActivity().findViewById(android.R.id.list);
        Assert.assertTrue(recyclerView.getAdapter().getItemCount() == Swarm.getInstance().getAllBees().size());

    }

    //TODO check data is shown into recyclerview

    //TODO check hit button pressed and corresponding bee hitpoints decreased

    //TODO check new swarm is shown after hitting to death last bee

    //TODO check swarm still shown after activity re-creation (rotation for example)
}
