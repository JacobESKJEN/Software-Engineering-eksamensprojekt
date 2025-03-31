package dtu.projectapp.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectApp {
    private List<Employee> employees;
    private List<Project> projects;

    public ProjectApp() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public void setProjects(List<Project> list) {
        projects = list;
    }
}
