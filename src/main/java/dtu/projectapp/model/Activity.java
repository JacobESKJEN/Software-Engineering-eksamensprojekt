package dtu.projectapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budgetedTime;               // expected hours worked
    private double hoursWorked = 0;            // total hours worked
    private List<Employee> employees = new ArrayList<>();

    public Activity(String name, LocalDate startDate, LocalDate endDate, double budgetedTime) {
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
