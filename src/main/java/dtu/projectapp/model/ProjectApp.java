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
            projects.add(new Project(evt.getNewValue().toString()));
            support.firePropertyChange("Projects", prevProjects, projects);
        } else if (evt.getPropertyName().equals("Login request")) {
            LogInPage logInPage = (LogInPage) evt.getSource();
            try {
                login(logInPage.getIdInput(), logInPage.getPasswordInput());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (evt.getPropertyName().equals("SetProjectLeader")) {
            Employee employee = findEmployee((String) evt.getNewValue());
            Project project = (Project) evt.getOldValue(); // Ændr dette så det ikke er oldValue der er projektet
            try {
                project.setProjectLeader(loggedInEmployee, employee);
                support.firePropertyChange("ProjectLeaderChanged", null, project.getProjectLeader().getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
            support.firePropertyChange("login", false, true);
        } else {
            throw new Exception("Invalid login");
        }
    }

    public String getLoggedInEmployeeId() {
        return loggedInEmployee.getId();
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

    public Activity getActivities() {
        return activity;
    }

    public void addActivity(Activity activity) {
    }
}
