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
    //private List<Employee> employees = new ArrayList<>(); // List of employees in the project
    PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public Project(String name, String id) {
        this.name = name;
        this.activities = new ArrayList<>();
        this.id = id;
    }

    public void setProjectLeader(Employee setterEmployee, Employee projLead) throws Exception {
        if (projectLeader != null && !(projectLeader.equals(setterEmployee))) {
            throw new Exception("Project already has a leader");
        } else if (projLead == null) {
            throw new Exception("Can't find employee");
        }
        projectLeader = projLead;
        support.firePropertyChange("New project leader", null, projectLeader.getId());
    }

    

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeStatus() throws Exception {
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

    public Activity findActivity(String activityName) {
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    public String getProjectETA() throws Exception {
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

            // stores all the info in the report string
            report.append("- ").append(activity.getName()).append(":\n")
                    .append("       ").append(logged).append(" hours logged\n")
                    .append("       ").append(remaining).append(" hours remaining\n")
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
    public String getProjectReport() throws Exception {
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }
        String employeeStatus = getEmployeeStatus();
        String projectETA = getProjectETA();
        return employeeStatus + "\n\n" + projectETA;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void createActivity(String activityName, String date, String date2, int i) {
        Activity activity = new Activity(activityName, LocalDate.parse(date), LocalDate.parse(date2), i);
        activities.add(activity);
    }

    public String findActivityName(String activityName) {
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                String A = activity.getName();
                return A;
            }
        }
        return null; // Return null if not found
    }
    public void removeActivity(String activityName) throws Exception {
        Activity activityToRemove = findActivity(activityName);
        if (activityToRemove == null) {
            throw new Exception("Activity not found");
        }
        activities.remove(activityToRemove);
    }
}
