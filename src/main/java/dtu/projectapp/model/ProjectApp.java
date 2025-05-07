package dtu.projectapp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dtu.projectapp.ui.LogInPage;

public class ProjectApp implements PropertyChangeListener {
    private List<Employee> employees;
    private List<Project> projects;

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

        employees.add(new Employee("huba", 0));
        employees.add(new Employee("w", 0));
    }

    public void login(String id) throws Exception {
        Employee emp = findEmployee(id);
        if (emp != null) {
            loggedInEmployee = emp;
        } else {
            throw new Exception("Invalid login");
        }
    }

    public void logout() throws Exception {
        if (loggedInEmployee == null) {
            throw new Exception("Not possible to log out");
        }
        loggedInEmployee = null;
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

    public Activity findActivity(String projectName, String activityName) {
    // Find the project and activity by name
    Project project = findProject(projectName);
    if (project != null) {
    Activity activity = project.findActivity(activityName);
    if (activity != null) {
    return activity;
    }
    }
    return null;
    }

    public void createProject(String name) throws Exception {
        if (findProject(name) == null) {
            projects.add(new Project(name, "2025" + projects.size() + 1));
            support.firePropertyChange("Update projects", null, projects);
        } else {
            throw new Exception("Project already exists");
        }
    }

    public void createActivity(String projectName, String activityName, String startDate, String endDate, int time)
            throws Exception {
        Project project = findProject(projectName);
        if (project == null) {
            throw new Exception("Project not found: " + projectName);
        }

        project.createActivity(activityName, startDate, endDate, time);
        // Fire property change event to notify observers (could be specific to
        // activities)
        support.firePropertyChange("New activity", null, project.getActivities());

    }
    public void RemoveActivity(String projectName, String activityName) throws Exception {
        Project project = findProject(projectName);
        if (project == null) {
            throw new Exception("Project not found: " + projectName);
        }

        project.removeActivity(activityName);
        // Fire property change event to notify observers (could be specific to
        // activities)
        support.firePropertyChange("Remove activity", null, project.getActivities());

    }


    public List<Project> getProjects() {
        return projects;
    }

}
