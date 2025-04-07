package dtu.projectapp.model;

import java.util.List;
import java.util.ArrayList;

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
        
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
