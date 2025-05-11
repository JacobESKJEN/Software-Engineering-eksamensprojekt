package dtu.projectapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Employee {
    private String id;

    private List<Activity> activities = new ArrayList<>();
    private Map<Activity, Double> hoursPerActivity = new HashMap<>();

    public Employee(String id) throws Exception {
        if (id.length() > 4) {
            throw new IllegalArgumentException("Maximum length of employee id is 4");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void assignActivity(Activity activity) {
        activities.add(activity);
        hoursPerActivity.put(activity, 0.0);
    }

    public void unAssignActivity(Activity activity) {
        activities.remove(activity);
        hoursPerActivity.remove(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean isAvailableBetweenWeeks(int startWeek, int endWeek, int startYear, int endYear) {
        int activitiesDuringInterval = 0;
        for (Activity activity : activities) {
            int activityStartTotalWeeks = WeekYearConversions.totalWeeks(activity.getStartWeek(),
                    activity.getStartYear());
            int activityEndTotalWeeks = WeekYearConversions.totalWeeks(activity.getEndWeek(), activity.getEndYear());
            int startTotalWeeks = WeekYearConversions.totalWeeks(startWeek, startYear);
            int endTotalWeeks = WeekYearConversions.totalWeeks(endWeek, endYear);

            boolean activityWithinInterval = (activityStartTotalWeeks >= startTotalWeeks
                    && activityStartTotalWeeks <= endTotalWeeks) ||
                    (activityEndTotalWeeks >= startTotalWeeks && activityEndTotalWeeks <= endTotalWeeks) ||
                    (activityStartTotalWeeks <= startTotalWeeks && activityEndTotalWeeks >= endTotalWeeks);

            if (activityWithinInterval) {
                activitiesDuringInterval++;
            }
        }

        return activitiesDuringInterval < 20;
    }

    public void logWork(Activity activity, double hours) throws Exception {
        if (hours % 0.5 != 0.0) {
            throw new Exception("Time is given in half hour intervals");
        }

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
