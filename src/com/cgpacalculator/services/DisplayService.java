package com.cgpacalculator.services;

import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.cgpacalculator.models.Course;
import com.cgpacalculator.models.Semester;

public class DisplayService {
    private Semester semester;
    private List<String[]> table;
    private String[] tableHeader = { "COURSE & CODE", "COURSE UNIT", "GRADE", "GRADE-UNIT" };
    private Map<Integer, Integer> columnWidth;

    public DisplayService(Semester semester) {
        this.semester = semester;
        this.table = this.toTable(semester);
        this.columnWidth = this.calculateColumnWidth();

    }

    public void setTableHeader(String[] header) {
        this.tableHeader = header;
    }

    public String[] getTableHeader() {
        return this.tableHeader;
    }

    private List<String[]> toTable(Semester semester) {
        Course[] allCourses = semester.getCourses();
        List<String[]> table = new ArrayList<String[]>();
        table.add(this.tableHeader);
        for (int i = 0; i < allCourses.length; i++) {
            table.add(allCourses[i].toTableRow());
        }

        return table;
    }

    private Map<Integer, Integer> calculateColumnWidth() {
        Map<Integer, Integer> columnLengths = new HashMap<>();
        this.table.stream().forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        return columnLengths;
    }

    private StringBuilder generateFormatString(Boolean isLeftJustified) {
        final StringBuilder formatString = new StringBuilder("");
        String flag = isLeftJustified ? "-" : "";
        this.columnWidth.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
        return formatString;
    }

    private String generateRowSeparator() {
        String line = this.columnWidth.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        return line;
    }

    public void displayTable(Boolean isLeftJustified) {
        if (isLeftJustified == null) {
            isLeftJustified = false;
        }

        StringBuilder formatString = this.generateFormatString(isLeftJustified);
        String separator = this.generateRowSeparator();

        this.displayTableHeader(formatString, separator);
        this.displayTableBody(formatString, separator);
        this.displayTableFooter(formatString, separator);
    }

    private void displayTableHeader(StringBuilder formatString, String separator) {
        System.out.print(separator);
        this.table.stream().limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(separator);
    }

    private void displayTableBody(StringBuilder formatString, String separator) {
        Stream.iterate(1, (i -> i < table.size()), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table.get(a)));
        System.out.print(separator);
    }

    private void displayTableFooter(StringBuilder formatString, String separator) {
        System.out.printf(formatString.toString(), "Total CGPA", this.formatSemesterResult(), "", "");
        System.out.printf(separator);
    }

    private String formatSemesterResult() {
        DecimalFormat df = new DecimalFormat("0.00");
        float result = this.semester.getGPA();
        return df.format(result);
    }

}
