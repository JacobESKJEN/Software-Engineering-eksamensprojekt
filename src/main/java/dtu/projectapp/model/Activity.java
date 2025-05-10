package dtu.projectapp.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
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
    private double budgetedTime;               // expected hours worked
    private double hoursWorked = 0;            // total hours worked
    private List<Employee> employees = new ArrayList<>();

    public Activity(String name, int startWeek, int endWeek, int startYear, int endYear, double budgetedTime) {
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
        }
        this.endWeek = endDate;
        this.endYear = endyear;
    }

    private static LocalDate weekToDate(int year, int weekNumber) {
        if (weekNumber < 1 || weekNumber > 53) {
            throw new IllegalArgumentException("Week number must be between 1 and 53");
        }
        return LocalDate.of(year, 1, 4)  // The 4th Jan is always in week 1 of ISO
                .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNumber)
                .with(WeekFields.ISO.dayOfWeek(), 1);
    }
    
    public double getBudgetedTime() {
        return budgetedTime;
    }
    public void setBudgetedTime(double budgeted) throws Exception {
        if (budgeted < 0) {
            throw new Exception("time out of bounds");
        }
        this.budgetedTime = budgeted;
    }

    public void setLoggedHours(double hours){       //sums the hours the empoloyee loggs
        this.hoursWorked += hours;
    }

    public double getHoursWorked(){
        return hoursWorked;
    }

    public double getCompletionPercentage() {
        if (budgetedTime == 0) return 0;            // safety, no dividing by 0
        return (hoursWorked / budgetedTime) * 100;
    }


    public double getRemainingHours(){
        return budgetedTime - hoursWorked;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    
    public void addEmployeeToActivity(Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("No such employee exists");
        }else if (employees.contains(employee)) {
            throw new Exception("Employee already assigned to activity");
        } 
        employees.add(employee);
    }
    public void removeEmployee(Employee employee) throws Exception {
        if (!employees.contains(employee)) {
            throw new Exception("No such employee assigned to activity");
        }
        employees.remove(employee);
    }
    public int getEmployeesAmount() {
        return employees.size();
    }

}
