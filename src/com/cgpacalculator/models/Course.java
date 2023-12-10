package com.cgpacalculator.models;

import com.cgpacalculator.interfaces.*;
import com.cgpacalculator.utils.*;

public class Course implements ICourse {
    private int creditUnit;
    private byte gradeValue;
    private String name;
    private int score;
    private char grade;

    public Course(String name, int creditUnit, int score) {
        this.name = name;
        this.creditUnit = creditUnit;
        this.score = score;
        this.grade = InputValidator.parseScoreToGrade(score);
        this.gradeValue = InputValidator.parseScoreToGradeValue(score);
    }

    public int getQualityPoint() {
        return this.creditUnit * this.gradeValue;
    }

    public int getCreditUnit() {
        return this.creditUnit;
    }

    public int getGradeValue() {
        return this.gradeValue;
    }

    public String getName() {
        return this.name;
    }

    public char getGrade() {
        return this.grade;
    }

    public int getScore() {
        return this.score;
    }

    public static Course create(String name, int creditUnit, int score) {
        return new Course(name, creditUnit, score);
    }

    public String[] toTableRow() {
        String creditUnit = String.format("%d", this.creditUnit);
        String grade = String.format("%c", this.grade);
        String gradeValue = String.format("%d", this.gradeValue);
        String[] arr = { name, creditUnit, grade, gradeValue };
        return arr;
    }
}
