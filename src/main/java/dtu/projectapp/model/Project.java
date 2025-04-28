package dtu.projectapp.model;

import java.util.List;
import java.util.ArrayList;

import javax.swing.tree.ExpandVetoException;

public class Project {
    private String name = "";
    private Employee projectLeader;
    private List<Activity> activities;

    public Project(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public void setProjectLeader(Employee setterEmployee, Employee projLead) throws Exception {
        if (projectLeader != null && !(projectLeader.equals(setterEmployee))) {
            throw new Exception("Project already has a leader");
        }
        projectLeader = projLead;
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
        report.append("=== Employee Contributions ===\n");
        report.append("Project name: ").append(getName()).append("\n");
        report.append("Project leader: ").append(getProjectLeader().getId()).append("\n\n");

        // Gets all the emplouyees logged hours (across all the activities) and the id
        for (Employee employee : Employee.getEmployees()) {
            double totalHours = employee.getTotalWork();
            report.append("- ID: ").append(employee.getId())
                .append(", Hours worked: ").append(totalHours).append("\n");
        }

        return report.toString();
    }





    public String getProjectETA() throws Exception {
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        StringBuilder report = new StringBuilder();
        report.append("Project Status\n");


        double totalHoursLogged = 0;
        double totalHoursRemaining = 0;

        for (Activity activity : activities){
            double logged = activity.getHoursWorked();
            double remaning = activity.getRemainingHours();
            double completion = activity.getCompletionPercentage();

            // stores all the info in the report string
            report.append("- ").append(activity.getName()).append(": ")
                                .append(logged).append(" hours logged ")
                                .append(remaning).append(" hours remaining")
                                .append(String.format("%.2f", completion)).append("% complete\n");

            totalHoursLogged += logged;
            totalHoursRemaining += remaning;
        }

        report.append("\nTotal hours logged for the project ").append(totalHoursLogged).append("\n");
        report.append("Expected total hours left: ").append(totalHoursRemaining).append("\n");
        return report.toString();

    }


    // This final/total report is the "sum" of getEmployeeStatus() and getProjectETA()
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
}
