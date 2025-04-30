package dtu.projectapp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import dtu.projectapp.ui.LogInPage;

public class ProjectApp implements PropertyChangeListener {
    private List<Employee> employees;
    private List<Project> projects;
    private Activity activity;
    private Employee loggedInEmployee;

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("project")) {
            List<Project> prevProjects = new ArrayList<Project>();
            prevProjects.addAll(projects);
            try {
                createProject(evt.getNewValue().toString());
                support.firePropertyChange("Projects", prevProjects, projects);
            } catch (Exception e) {
                System.out.println("Test");
                support.firePropertyChange("Exception", null, e.getMessage());
            }
        } else if (evt.getPropertyName().equals("Login request")) {
            LogInPage logInPage = (LogInPage) evt.getSource();
            try {
                // login(logInPage.getIdInput(), logInPage.getPasswordInput());
            } catch (Exception e) {
                support.firePropertyChange("Exception", null, e.getMessage());
            }
        } else if (evt.getPropertyName().equals("SetProjectLeader")) {
            Employee employee = findEmployee((String) evt.getNewValue());
            Project project = (Project) evt.getOldValue();
            try {
                project.setProjectLeader(loggedInEmployee, employee);
                support.firePropertyChange("ProjectLeaderChanged", null, project.getProjectLeader().getId());
            } catch (Exception e) {
                System.out.println("Project leader changed exception");
                support.firePropertyChange("Exception", null, e.getMessage());
            }
        }
    }

    public ProjectApp() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();

        employees.add(new Employee("huba", "password", 0));
        employees.add(new Employee("w", "w", 0));
    }

    public void login(String id, String password) throws Exception {
        Employee emp = findEmployee(id);
        if (emp != null && password.equals(emp.getCredential())) {
            loggedInEmployee = emp;
        } else {
            throw new Exception("Invalid login");
        }
    }

    public String getLoggedInEmployeeId() {
        return loggedInEmployee.getId();
    }

    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
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
        System.out.println(name);
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    public void createProject(String name) throws Exception {
        if (findProject(name) == null) {
            projects.add(new Project(name));
            support.firePropertyChange("Update projects", null, projects);
        } else {
            throw new Exception("Project already exists");
        }
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Activity getActivities() {
        return activity;
    }

    public void addActivity(Activity activity) {
    }
}
