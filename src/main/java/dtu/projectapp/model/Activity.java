package dtu.projectapp.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Activity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startWeek;
    private int endWeek;
    private double budgetedTime; // expected hours worked
    private double hoursWorked = 0; // total hours worked
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

    public long calculateWeeks() {
        LocalDate startWeek = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endWeek = endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // Calculate the number of weeks between the start and end weeks
        long weeksBetween = ChronoUnit.WEEKS.between(startWeek, endWeek);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        System.out.println("Start Week: " + startWeek.get(weekFields.weekOfWeekBasedYear()));
        System.out.println("End Week: " + endWeek.get(weekFields.weekOfWeekBasedYear()));
        System.out.println("Number of Weeks: " + weeksBetween);

        this.startWeek = startWeek.get(weekFields.weekOfWeekBasedYear());
        this.endWeek = endWeek.get(weekFields.weekOfWeekBasedYear());

        return weeksBetween;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
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
