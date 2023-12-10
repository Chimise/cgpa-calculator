package com.cgpacalculator.utils;

import com.cgpacalculator.interfaces.ICourse;

public class InputValidator {
    public static boolean isValidInteger(String input) {
        return !input.trim().isEmpty() && input.matches("\\d+");
    }

    public static boolean isValidScore(String input) {
        if (!isValidInteger(input)) {
            return false;
        }

        int parsedScore = parseToInt(input);
        return parsedScore >= 0 && parsedScore <= 100;
    }

    public static boolean isValidCourseName(String input) {
        input = input.trim();
        return !input.isEmpty() && input.matches("^[a-zA-Z]{3}[- ]?[0-9]{3}$");
    }

    public static boolean isValidCreditLoad(String input) {
        input = input.trim();
        Boolean isValid = !input.isEmpty() && isValidInteger(input);
        if (!isValid) {
            return false;
        }
        int parseInput = parseToInt(input);
        return parseInput > 0 && parseInput <= 10;
    }

    public static boolean isValidTotalCourses(String input) {
        if (!isValidInteger(input)) {
            return false;
        }

        int parsedValue = parseToInt(input);
        return parsedValue > 0 && parsedValue <= 30;
    }

    public static boolean isCourseNameUnique(String courseName, ICourse[] courses) {
        for (ICourse course : courses) {
            if (course == null) {
                continue;
            }
            if (course.getName().equalsIgnoreCase(courseName)) {
                return false;
            }
        }

        return true;
    }

    public static int parseToInt(String input) {
        return Integer.parseInt(input);
    }

    public static char parseScoreToGrade(int score) {
        char grade;
        if (score >= 70) {
            grade = 'A';
        } else if (score >= 60) {
            grade = 'B';
        } else if (score >= 50) {
            grade = 'C';
        } else if (score >= 45) {
            grade = 'D';
        } else if (score >= 40) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        return grade;
    }

    public static byte parseGradeToValue(char grade) {
        byte weight;
        switch (grade) {
            case 'A':
                weight = 5;
                break;
            case 'B':
                weight = 4;
                break;
            case 'C':
                weight = 3;
                break;
            case 'D':
                weight = 2;
                break;
            case 'E':
                weight = 1;
                break;
            default:
                weight = 0;
                break;
        }

        return weight;
    }

    public static byte parseScoreToGradeValue(int score) {
        char grade = parseScoreToGrade(score);
        return parseGradeToValue(grade);
    }

}
