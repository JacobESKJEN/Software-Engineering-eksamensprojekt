package dtu.projectapp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectApp {
    private List<Employee> employees;
    private List<Project> projects;

    private Employee loggedInEmployee;

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ProjectApp() {
        employees = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
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

    public void addEmployee(String id) throws Exception {
        if (findEmployee(id) != null) {
            throw new Exception("Employee already exists!");
        }
        Employee emp = new Employee(id);
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
            Project project = new Project(name, LocalDate.now().getYear() + "" + (projects.size() + 1));
            project.setPropertyChangeSupport(support);
            projects.add(project);
            support.firePropertyChange("Update projects", null, projects);
        } else {
            throw new Exception("Project already exists");
        }
    }

    public void createActivity(String projectName, String activityName, int startDate, int endDate, int year, int year2,
            double time)
            throws Exception {
        Project project = findProject(projectName);
        if (project == null) {
            throw new Exception("Project not found: " + projectName);
        }

        project.createActivity(activityName, startDate, endDate, year, year2, time);
        // Fire property change event to notify observers (could be specific to
        // activities)
        support.firePropertyChange("New activity", null, project.getActivities());

    }

    public void createSpecialActivity(String projectName, String activityName, String startDate, String endDate,
            Employee employee) throws Exception {
        Project project = findProject(projectName);
        if (project == null) {
            throw new Exception("Project not found: " + projectName);
        }

        project.createSpecialActivity(activityName, startDate, endDate, employee);
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

    public List<Employee> getAvailableEmployees(int startWeek, int endWeek, int startYear, int endYear) {
        List<Employee> availableEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.isAvailableBetweenWeeks(startWeek, endWeek, startYear, endYear)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    public List<Employee> getAvailableEmployees(Activity activity) {
        List<Employee> employeesWithTime = getAvailableEmployees(activity.getStartWeek(), activity.getEndWeek(),
                activity.getStartYear(), activity.getEndYear());
        List<Employee> availableEmployees = new ArrayList<>();
        for (Employee employee : employeesWithTime) {
            if (!employee.getActivities().contains(activity)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
