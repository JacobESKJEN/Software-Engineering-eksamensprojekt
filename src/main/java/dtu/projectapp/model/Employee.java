package dtu.projectapp.model;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    private String id;
    private int activitiesAssigned;

    private Map<Activity, Double> hoursPerActivity = new HashMap<>();

    public Employee(String id, int activitiesAssigned) {
        this.id = id;
        this.activitiesAssigned = activitiesAssigned;
    }

    public String getId() {
        return id;
    }

    public int getActivitiesAssigned() {
        return activitiesAssigned;
    }

    public void logWork(Activity activity, double hours) {
    if (!activity.getEmployees().contains(this)) {
        try {
            activity.addEmployeeToActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    hoursPerActivity.put(activity, hoursPerActivity.getOrDefault(activity, 0.0) + hours);

    
    activity.setLoggedHours(hours);
}

    public double getTotalWork() {
        double total = 0;
        for (double hours : hoursPerActivity.values()) {
            total += hours;
        }
        return total;
    }

    public Map<Activity, Double> getHoursWorkedPerActivity() {
        return hoursPerActivity;
    }
}
