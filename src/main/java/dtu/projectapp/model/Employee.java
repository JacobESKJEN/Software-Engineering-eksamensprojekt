package dtu.projectapp.model;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void logWork(Activity activity, double hours) { // tracks hours worked per activity
        hoursPerActivity.put(activity, hoursPerActivity.getOrDefault(activity, 0.0) + hours); // the Map is updated with
                                                                                              // the activity as a key
                                                                                              // and the hours worked as
                                                                                              // a value
        activity.setLoggedHours((hours));
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
