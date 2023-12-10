package com.cgpacalculator.models;

import com.cgpacalculator.services.DisplayService;

public class CgpaCalculator {
    private Semester currentSemester;

    public void setCurrentSemester(Semester semester) {
        currentSemester = semester;
    }

    public Semester getCurrentSemester() {
        if (this.currentSemester == null) {
            throw new IndexOutOfBoundsException("currentSemester property has not been set");
        }
        return currentSemester;
    }

    public void displaySemesterResult(Boolean isLeftJustified) {
        Semester semester = this.getCurrentSemester();
        DisplayService displayService = new DisplayService(semester);
        displayService.displayTable(isLeftJustified);
    }

}
