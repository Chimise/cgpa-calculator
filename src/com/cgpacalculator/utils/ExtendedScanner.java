package com.cgpacalculator.utils;

import java.util.Scanner;

import com.cgpacalculator.interfaces.ICourse;

public class ExtendedScanner {
    private Scanner scanner;

    public ExtendedScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getCourseScore(String prompt) {
        System.out.print(prompt);
        String inputScore = this.scanner.nextLine().trim();
        if (!InputValidator.isValidScore(inputScore)) {
            printErrorMessage("Invalid Input, Enter a number from 0 - 100");
            return getCourseScore(prompt);
        }

        int parsedScore = InputValidator.parseToInt(inputScore);
        return parsedScore;
    }

    public String getCourseName(String prompt, ICourse[] courses) {
        System.out.print(prompt);
        String inputCourseName = this.scanner.nextLine().trim();
        if (!InputValidator.isValidCourseName(inputCourseName)) {
            printErrorMessage("Invalid input, Enter a valid course e.g MTH 101, Chm-101, ENG101");
            return getCourseName(prompt, courses);
        }

        if (!InputValidator.isCourseNameUnique(inputCourseName, courses)) {
            printErrorMessage("Invalid input, similar course code already exist");
            return getCourseName(prompt, courses);
        }

        return inputCourseName;
    }

    public int getCourseCreditLoad(String prompt) {
        System.out.print(prompt);
        String inputCreditCourseLoad = this.scanner.nextLine().trim();
        if (!InputValidator.isValidCreditLoad(inputCreditCourseLoad)) {
            printErrorMessage("Invalid input, Enter a valid number from 1 - 10");
            return getCourseCreditLoad(prompt);
        }

        return InputValidator.parseToInt(inputCreditCourseLoad);
    }

    public int getAllCoursesInSemester(String prompt) {
        System.out.print(prompt);
        String totalCourses = this.scanner.nextLine().trim();
        if (!InputValidator.isValidTotalCourses(totalCourses)) {
            printErrorMessage("Invalid input, Enter a valid number from 1 - 30");
            return getAllCoursesInSemester(prompt);
        }

        return InputValidator.parseToInt(totalCourses);

    }

    public void close() {
        this.scanner.close();
    }

    private void printErrorMessage() {
        System.out.println("Invalid user input");
    }

    private void printErrorMessage(String message) {
        System.out.println(message);
    }

}
