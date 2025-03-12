package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private ArrayList<SportsActivity> activities;

    @BeforeEach
    void setUp() {
        activities = new ArrayList<>();
    }

    @Test
    void testLogActivity() {
        SportsActivity activity = new SportsActivity("Basketball", 60);
        activities.add(activity);
        assertEquals(1, activities.size());
        assertEquals("Basketball", activities.getFirst().sportName());
        assertEquals(60, activities.getFirst().duration());
    }

    @Test
    void testCalculateTotalTime() {
        activities.add(new SportsActivity("Running", 30));
        activities.add(new SportsActivity("Swimming", 45));
        int totalTime = activities.stream().mapToInt(SportsActivity::duration).sum();
        assertEquals(75, totalTime);
    }

    @Test
    void testViewActivities() {
        activities.add(new SportsActivity("Football", 90));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        for (SportsActivity activity : activities) {
            System.out.println(activity);
        }

        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Football"));
        assertTrue(outputStream.toString().contains("90 minutes"));
    }

    @Test
    void testDisplayPossibleOptions() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Main.displayPossibleOptions();
        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Sports Time Tracker"));
    }
}