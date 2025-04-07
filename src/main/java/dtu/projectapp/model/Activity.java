package dtu.projectapp.model;

import java.time.LocalDate;

public class Activity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int budgetedTime; // in hoours

    public Activity(String name, LocalDate startDate, LocalDate endDate, int budgetedTime) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetedTime = budgetedTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getBudgetedTime() {
        return budgetedTime;
    }
}
