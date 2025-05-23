package dtu.projectapp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Activity {

    private String name;
    private int startWeek;
    private int endWeek;
    private int startYear;
    private int endYear;
    private double budgetedTime; // expected hours worked
    private double hoursWorked = 0; // total hours worked
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<Employee> employees = new ArrayList<>();

    public Activity(String name, int startWeek, int endWeek, int startYear, int endYear, double budgetedTime)
            throws Exception {
        if (startWeek < 1 || startWeek > 53 || endWeek < 1 || endWeek > 53) {
            throw new Exception("Week must be between 1 and 53");
        }
        if (budgetedTime < 0) {
            throw new Exception("Budgeted time can't be negative");
        }
        if (WeekYearConversions.totalWeeks(startWeek, startYear) > WeekYearConversions.totalWeeks(endWeek, endYear)) {
            throw new Exception("End date must be after start date");
        }
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startYear = startYear;
        this.endYear = endYear;
        this.budgetedTime = budgetedTime;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport support) {
        this.support = support;
    }

    public void addObserver(PropertyChangeListener listener) { // Jacob
        support.addPropertyChangeListener(listener);
    }

    public void setName(String name) { // Noah
        this.name = name;
        support.firePropertyChange("Name change", null, name);
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

    public void setEndDate(int endDate, int endyear) throws IllegalArgumentException { // Jacob
        if (endyear < startYear) {
            throw new IllegalArgumentException("End year must be greater than or equal to start year.");
        }
        if (WeekYearConversions.totalWeeks(startWeek, startYear) > WeekYearConversions.totalWeeks(endDate, endYear)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.endWeek = endDate;
        this.endYear = endyear;
        support.firePropertyChange("End date change", null, this);
    }

    public double getBudgetedTime() {
        return budgetedTime;
    }

    public void setBudgetedTime(double budgeted) throws Exception { // Noah
        if (budgeted < 0) {
            throw new Exception("time out of bounds");
        }
        if (budgeted % 0.5 != 0.0) {
            throw new Exception("Time is given in half hour intervals");
        }
        this.budgetedTime = budgeted;
        support.firePropertyChange("Update budgeted time", null, this.budgetedTime);
    }

    public void setLoggedHours(double hours) { // sums the hours the empoloyee registers Oliver
        this.hoursWorked += hours;
        support.firePropertyChange("Update budgeted time", null, this.budgetedTime);
    }

    public double getHoursWorked() { // Oliver
        return hoursWorked;
    }

    public double getCompletionPercentage() { // Oliver
        if (budgetedTime == 0) {
            return 0; // safety, no dividing by 0

        }
        return (hoursWorked / budgetedTime) * 100;
    }

    public double getRemainingHours() { // oliver
        return budgetedTime - hoursWorked;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployeeToActivity(Employee employee) throws Exception { // Jacob
        if (employee == null) {
            throw new Exception("No such employee exists");
        } else if (employees.contains(employee)) {
            throw new Exception("Employee already assigned to activity");
        }
        employee.assignActivity(this);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) throws Exception { // Noah
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
