package org.example;

public record SportsActivity(String sportName, int duration) {

    @Override
    public String toString() {
        return "Sport: " + sportName + ", Duration: " + duration + " minutes";
    }
}
