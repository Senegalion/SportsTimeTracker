package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SportsActivityTest {
    @Test
    void testSportsActivityToString() {
        SportsActivity activity = new SportsActivity("Tennis", 45);
        String expected = "Sport: Tennis, Duration: 45 minutes";
        assertEquals(expected, activity.toString());
    }

    @Test
    void testSportsActivityGetters() {
        SportsActivity activity = new SportsActivity("Cycling", 120);
        assertEquals("Cycling", activity.sportName());
        assertEquals(120, activity.duration());
    }
}