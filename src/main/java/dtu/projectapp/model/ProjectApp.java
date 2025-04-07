package dtu.projectapp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ProjectApp implements PropertyChangeListener {
    private List<Employee> employees;
    private List<Project> projects;

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("project")) {
            List<Project> prevProjects = new ArrayList<Project>();
            prevProjects.addAll(projects);
            projects.add(new Project(evt.getNewValue().toString()));
            support.firePropertyChange("Projects", prevProjects, projects);
        }
    }

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

    public Employee findEmployee(String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
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
