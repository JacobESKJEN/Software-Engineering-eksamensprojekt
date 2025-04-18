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

    public void getEmployeeStatus() throws Exception {
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        // project name
        System.err.println(getName());

        // project leader
        System.err.println(getProjectLeader());

    }

    public void getProjectETA() throws Exception {
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        // Activity.loggedHours() (for all activities)
        // Activity.remainingHours()
        // Activity.loggedHoursTotal()
        // Activity.remainingHoursTotal()

    }

    /**
     * @details This is the total report. This is a combination of
     *          getEmplyeeStatus() and getProjectETA()
     */
    public void getProjectReport() throws Exception {
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }
        getEmployeeStatus();
        getProjectETA();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
