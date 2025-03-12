package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<SportsActivity> activities = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        appLoop();
    }

    static void appLoop() {
        while (true) {
            displayPossibleOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> logActivity();
                case 2 -> viewActivities();
                case 3 -> calculateTotalTime();
                case 4 -> System.out.println("Exiting application.");
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    static void displayPossibleOptions() {
        System.out.println("\nSports Time Tracker");
        System.out.println("1. Log a sport activity");
        System.out.println("2. View logged activities");
        System.out.println("3. Calculate total time spent on sports");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void logActivity() {
        System.out.print("Enter sport name: ");
        String sportName = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        activities.add(new SportsActivity(sportName, duration));
        System.out.println("Activity logged successfully!");
    }

    private static void viewActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
        } else {
            System.out.println("\nLogged Activities:");
            for (SportsActivity activity : activities) {
                System.out.println(activity);
            }
        }
    }

    private static void calculateTotalTime() {
        int totalTime = activities.stream().mapToInt(SportsActivity::duration).sum();
        System.out.println("Total time spent on sports this week: " + totalTime + " minutes");
    }
}