package com.cgpacalculator.main;

import java.util.Scanner;

import com.cgpacalculator.models.CgpaCalculator;
import com.cgpacalculator.models.Semester;
import com.cgpacalculator.utils.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ExtendedScanner extendedScanner = new ExtendedScanner(scanner);

    public static void main(String[] args) {
        displayWelcomeMessage();
        int totalCoursesForSemester = extendedScanner
                .getAllCoursesInSemester("Enter the total number of courses for the semester: ");
        System.out.println();

        Semester semester = new Semester(totalCoursesForSemester);
        CgpaCalculator cgpaCalculator = new CgpaCalculator();

        for (int i = 0; i < totalCoursesForSemester; i++) {
            String courseName = extendedScanner.getCourseName(
                    String.format("Enter the code for Course #%d: ", i + 1),
                    semester.getCourses());
            int courseCreditLoad = extendedScanner
                    .getCourseCreditLoad(String.format("Enter the credit unit for %s: ", makeBold(courseName)));
            int score = extendedScanner
                    .getCourseScore(String.format("Enter your score for %s: ", makeBold(courseName)));
            System.out.println();
            semester.addCourse(courseName, courseCreditLoad, score);
        }

        extendedScanner.close();
        cgpaCalculator.setCurrentSemester(semester);
        cgpaCalculator.displaySemesterResult(null);

    }

    public static void displayWelcomeMessage() {
        System.out.println(makeBold("Welcome to this CGPA Calculator"));
        System.out.println();
    }

    public static String makeBold(String text) {
        return String.format("\u001B[1m%s\u001B[0m", text);
    }
}
