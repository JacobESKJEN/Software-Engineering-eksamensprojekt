package dtu.projectapp.model;

public class Project {
    private String name = "";
    private Employee projectLeader;

    public Project(String name) {
        this.name = name;
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
}
