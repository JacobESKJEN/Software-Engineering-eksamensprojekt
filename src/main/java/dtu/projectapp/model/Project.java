package dtu.projectapp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Project {

    private String name = "";
    private String id;
    private Employee projectLeader;
    private List<Activity> activities;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setPropertyChangeSupport(PropertyChangeSupport support) {
        this.support = support;
    }

    public Project(String name, String id) {
        this.name = name;
        this.activities = new ArrayList<>();
        this.id = id;
    }

    public String getId() { // Jacob
        return id;
    }

    public void setProjectLeader(Employee setterEmployee, Employee projLead) throws Exception { // Jacob
        if (projectLeader != null && !(projectLeader.equals(setterEmployee))) {
            throw new Exception("Project already has a leader");
        } else if (projLead == null) {
            throw new Exception("Can't find employee");
        }
        projectLeader = projLead;
        support.firePropertyChange("New project leader", null, projectLeader.getId());
    }

    public Employee getProjectLeader() { // Jacob
        return projectLeader;
    }

    public String getName() { // Jacob
        return name;
    }

    public String getEmployeeStatus() throws Exception { // Oliver
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        StringBuilder report = new StringBuilder();

        // Get project name, leader id
        report.append(" -- Employee Contributions --\n");
        report.append("Project name: ").append(getName()).append("\n");
        report.append("Project leader: ").append(getProjectLeader().getId()).append("\n\n");

        Set<Employee> projectEmployees = new HashSet<>();

        for (Activity activity : getActivities()) {
            projectEmployees.addAll(activity.getEmployees());
        }

        // Gets all the emplouyees logged hours per activity, total hours and the id
        for (Employee employee : projectEmployees) {
            report.append("- ID: ").append(employee.getId()).append("\n");

            double totalHours = 0;

            for (Map.Entry<Activity, Double> entry : employee.getHoursWorkedPerActivity().entrySet()) {
                Activity activity = entry.getKey();
                double hours = entry.getValue();

                if (activities.contains(activity)) {
                    report.append("   - ").append(activity.getName()).append(": ").append(hours).append(" hours\n");
                    totalHours += hours;
                }
            }

            report.append("    Total: ").append(totalHours).append(" hours\n\n");

        }

        return report.toString();
    }

    public Activity findActivity(String activityName) { // Alexander
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    public void changeActivityName(Activity activity, String newName) throws Exception { // Jacob
        if (findActivity(newName) != null) {
            throw new Exception("Activity already exists");
        }
        activity.setName(newName);
    }

    public boolean isActivitySpecial(String activityName) { // Alexander
        Activity activity = findActivity(activityName);
        return activity instanceof SpecialActivity;
    }

    public String getProjectETA() throws Exception { // Oliver
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        StringBuilder report = new StringBuilder();
        report.append(" -- Project Status --\n");

        double totalHoursLogged = 0;
        double totalHoursRemaining = 0;

        for (Activity activity : activities) {
            double logged = activity.getHoursWorked();
            double remaining = activity.getRemainingHours();
            double completion = activity.getCompletionPercentage();
            long weeks = WeekYearConversions.calculateWeeksBetween(activity.getStartWeek(), activity.getEndWeek(),
                    activity.getStartYear(), activity.getEndYear());
            int start = activity.getStartWeek();
            int end = activity.getEndWeek();

            // stores all the info in the report string
            report.append("- ").append(activity.getName()).append(":\n")
                    .append("       ").append(logged).append(" hours logged\n")
                    .append("       ").append(remaining).append(" hours remaining\n")
                    .append("       ").append(start + "-" + end).append(" WeekPlan\n")
                    .append("       ").append(weeks).append(" Total Weeks\n")
                    .append("       ").append(String.format("%.2f", completion)).append("% complete\n");

            totalHoursLogged += logged;
            totalHoursRemaining += remaining;
        }

        report.append("\nTotal hours logged for the project ").append(totalHoursLogged).append("\n");
        report.append("Expected total hours left: ").append(totalHoursRemaining).append("\n");
        return report.toString();

    }

    // This final/total report is the "sum" of getEmployeeStatus() and
    // getProjectETA()
    public String getProjectReport() throws Exception { // Oliver
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }
        String employeeStatus = getEmployeeStatus();
        String projectETA = getProjectETA();
        return employeeStatus + "\n\n" + projectETA;
    }

    public void addActivity(Activity activity) { // Alexander
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void createActivity(String activityName,
            int date, int date2,
            int year, int year2,
            double i)
            throws Exception { // Noah
        if (!(i % 0.5 == 0)) { // 1
            throw new Exception("Time must be in 30 min intervals");
        } else if (findActivity(activityName) != null) { // 2
            throw new Exception("Activity already exists");
        } else if (date < 1 || date2 > 53 || date2 < 1 || date > 53) {
            throw new Exception("Week must be between 1 and 53");
        } else if (WeekYearConversions.totalWeeks(date, year) > WeekYearConversions.totalWeeks(date2, year2)) {
            throw new Exception("End date must be after start date");
        }
        try {
            Activity activity = new Activity(activityName,
                    date, date2,
                    year, year2,
                    i);
            activity.setPropertyChangeSupport(support);
            activities.add(activity);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createSpecialActivity(String activityName, String date, String date2, Employee employee)
            throws Exception { // Noah
        if (employee == null) {
            throw new Exception("Unable to find employee");
        } else if (findActivity(activityName) != null) {
            throw new Exception("Activity already exists");
        } else if (date == null || date2 == null) {
            throw new Exception("Date cannot be null");
        } else if (LocalDate.parse(date).isAfter(LocalDate.parse(date2))) {
            throw new Exception("End date must be after start date");
        }

        Activity activity = new SpecialActivity(activityName, LocalDate.parse(date), LocalDate.parse(date2), employee);
        activities.add(activity);
    }

    public void removeActivity(String activityName) throws Exception { // Alexander
        try {
            Activity activityToRemove = findActivity(activityName);
            for (Employee employee : activityToRemove.getEmployees()) {
                employee.unAssignActivity(activityToRemove);
            }
            activities.remove(activityToRemove);
        } catch (Exception e) {
            throw new Exception("Activity not found");
        }
    }
}
