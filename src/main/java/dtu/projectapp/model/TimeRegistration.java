package dtu.projectapp.model;

import java.time.LocalDate;

public class TimeRegistration {
    private String employeeId;
    private String activityName;
    private int hoursWorked;
    private LocalDate date; // Format: YYYY-MM-DD

    public TimeRegistration(String employeeId, String activityName, int hoursWorked, LocalDate date) {
        this.employeeId = employeeId;
        this.activityName = activityName;
        this.hoursWorked = hoursWorked;
        this.date = date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getActivityName() {
        return activityName;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public LocalDate getDate() {
        return date;
    }

    
    
}
