package dtu.projectapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int budgetedTime; // in hoours
    private List<Employee> employees = new ArrayList<>(); //employees in activit

    public Activity(String name, LocalDate startDate, LocalDate endDate, int budgetedTime) {
        // if (endDate.isBefore(startDate)) {
        //     throw new IllegalArgumentException("End date must be after start date");
        // }
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public boolean isEmployeeAssigned(Employee employee) {
        return employees.contains(employee);
    }
    //// for use in cumber tests
    //// Example methods to check activity status
    // public boolean isOverBudget() {
    //     // Assuming budgetedTime is in hours and we want to check if it exceeds a certain limit
    //     int maxBudgetedTime = 40; // Example limit
    //     return budgetedTime > maxBudgetedTime;
    // }
    // public boolean isInProgress() {
    //     Calendar now = Calendar.getInstance();
    //     return startDate.before(now) && endDate.after(now);
    // }
    // public boolean isCompleted() {
    //     Calendar now = Calendar.getInstance();
    //     return endDate.before(now);
    // }
    

}
