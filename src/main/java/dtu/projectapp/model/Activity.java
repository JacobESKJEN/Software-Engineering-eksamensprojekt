package dtu.projectapp.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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
    public void setName(String name) {
        this.name = name;
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

    public void setEndDate(LocalDate endDate) throws IllegalArgumentException {
        if (endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.endDate = endDate;
    }

    public long calculateWeeks(LocalDate startDate, LocalDate endDate) {
        // Find the first day of the week for the start date
        LocalDate startWeek = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        
        // Find the last day of the week for the end date
        LocalDate endWeek = endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        
        // Calculate the number of weeks between the start and end weeks
        long weeksBetween = ChronoUnit.WEEKS.between(startWeek, endWeek);
        
        System.out.println("Start Week: " + startWeek);
        System.out.println("End Week: " + endWeek);
        System.out.println("Number of Weeks: " + weeksBetween);

        return weeksBetween;
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
