package dtu.projectapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startWeek;
    private int endWeek;
    private int startYear;
    private int endYear;
    private double budgetedTime; // expected hours worked
    private double hoursWorked = 0; // total hours worked
    private List<Employee> employees = new ArrayList<>();

    public Activity(String name, int startWeek, int endWeek, int startYear, int endYear, double budgetedTime){
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startYear = startYear;
        this.endYear = endYear;
        this.budgetedTime = budgetedTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndDate(int endDate, int endyear) throws IllegalArgumentException {
        if (endyear < startYear) {
            throw new IllegalArgumentException("End year must be greater than or equal to start year.");
        } else if (endyear >= startYear && endDate < startWeek) {
            throw new IllegalArgumentException("End week must be greater than or equal to start week.");
        } // Måske ændr til at bare at beregne totalweeks og om den ene totalweeks er
          // større eller mindre end den anden. F.eks.
          // if (WeekYearConversions.totalWeeks(startWeek, startYear) <=
          // WeekYearConversions.totalWeeks(endWeek, endYear)) {
          // throw new Exception("End date must be after start date");
          // }
        this.endWeek = endDate;
        this.endYear = endyear;
    }

    // public long calculateWeeks() {
    // // Calculate the number of weeks between the start and end weeks
    // long weeksBetween = 0;
    // if (startYear == endYear) {
    // weeksBetween = endWeek - startWeek;
    // } else {
    // int startTotalWeeks = startYear * 53 + startWeek;
    // int endTotalWeeks = endYear * 53 + endWeek;
    // weeksBetween= endTotalWeeks - startTotalWeeks;
    // }
    // return weeksBetween;
    // }

    public double getBudgetedTime() {
        return budgetedTime;
    }

    public void setBudgetedTime(double budgeted) throws Exception {
        if (budgeted < 0) {
            throw new Exception("time out of bounds");
        }
        this.budgetedTime = budgeted;
    }

    public void setLoggedHours(double hours) { // sums the hours the empoloyee loggs
        this.hoursWorked += hours;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getCompletionPercentage() {
        if (budgetedTime == 0)
            return 0; // safety, no dividing by 0
        return (hoursWorked / budgetedTime) * 100;
    }

    public double getRemainingHours() {
        return budgetedTime - hoursWorked;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployeeToActivity(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("No such employee exists");
        } else if (employees.contains(employee)) {
            throw new Exception("Employee already assigned to activity");
        }
        employee.assignActivity(this);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) throws Exception {
        if (!employees.contains(employee)) {
            throw new Exception("No such employee assigned to activity");
        }
        employee.unAssignActivity(this);
        employees.remove(employee);
    }

    public int getEmployeesAmount() {
        return employees.size();
    }

}
