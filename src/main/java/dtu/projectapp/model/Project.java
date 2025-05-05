package dtu.projectapp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Project {
    private String name = "";
    private Employee projectLeader;
    private List<Activity> activities;

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public Project(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
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
        
        // Gets all the emplouyees logged hours per activity, total hours and the id
        for (Employee employee : Employee.getEmployees()) {
            report.append("- ID: ").append(employee.getId()).append("\n");

            for (Map.Entry<Activity, Double> entry : employee.getHoursWorkedPerActivity().entrySet()) {
                Activity activity = entry.getKey();
                double hours = entry.getValue();

                report.append("   - ").append(activity.getName()).append(": ").append(hours).append(" hours\n");
            }

            double totalHours = employee.getTotalWork();
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
            report.append("- ").append(activity.getName()).append(": ")
                    .append(logged).append(" hours logged ")
                    .append(remaining).append(" hours remaining")
                    .append(String.format("%.2f", completion)).append("% complete\n");

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
        Activity activity = new Activity(activityName,LocalDate.parse(date),LocalDate.parse(date2),i);
        activities.add(activity);
    }
   
}
