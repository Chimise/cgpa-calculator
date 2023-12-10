package com.cgpacalculator.models;

public class Semester {
    private Course[] courses;
    private int currentIndex;
    private int totalQualityPoint;
    private int totalCreditUnit;

    public Semester(int totalCourses) {
        this.courses = new Course[totalCourses];
    }

    public void addCourse(String courseName, int creditCourseLoad, int courseScore) {
        if (currentIndex >= this.courses.length) {
            System.out.println("You have entered all the scores");
            return;
        }

        Course course = Course.create(courseName, creditCourseLoad, courseScore);

        this.courses[this.currentIndex] = course;
        this.currentIndex++;
        this.totalQualityPoint += course.getQualityPoint();
        this.totalCreditUnit += course.getCreditUnit();
    }

    public Course[] getCourses() {
        return this.courses;
    }

    public int getTotalQualityPoint() {
        return this.totalQualityPoint;
    }

    public float getGPA() {
        return (float) this.totalQualityPoint / this.totalCreditUnit;
    }

    public int getTotalCreditUnit() {
        return this.totalCreditUnit;
    }

    public int getTotalCourses() {
        return this.courses.length;
    }

}