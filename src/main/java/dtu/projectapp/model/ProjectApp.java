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

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public Project findProject(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    public void createProject(Employee emp, String name) throws Exception {
        if (findProject(name) == null) {
            projects.add(new Project(name));
        } else {
            throw new Exception("Project already exists");
        }
    }

    public List<Project> getProjects() {
        return projects;
    }
}
