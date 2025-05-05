package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.projectapp.model.*;

import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.util.*;

public class ProjectReports {

    private Project project;
    private Map<String, Activity> activityMap = new HashMap<>();
    private Map<String, Employee> employeeMap = new HashMap<>();
    private String report;
    private ProjectApp projectApp;
    private ErrorMessageHolder errorMessageHolder;

    public ProjectReports(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("signed-in as Project leader with id {string}")
    public void signed_in_as_project_leader_with_id(String id) throws Exception {
        Employee leader = new Employee(id, 0);
        employeeMap.put(id, leader);
        project = new Project("Unnamed", "20251");
        project.setProjectLeader(leader, leader);
    }

    @And("there exists a project with name {string}")
    public void there_exists_a_project_with_name(String name) {
        if (project == null) {
            project = new Project(name, "20251");
        } else {
        }
    }

    @And("the project {string} has activities {string} and {string}")
    public void the_project_has_activities(String projectName, String activity1, String activity2) {
        Activity a1 = new Activity(activity1, LocalDate.now(), LocalDate.now().plusDays(5), 10);
        Activity a2 = new Activity(activity2, LocalDate.now(), LocalDate.now().plusDays(5), 10);
        project.addActivity(a1);
        project.addActivity(a2);
        activityMap.put(activity1, a1);
        activityMap.put(activity2, a2);
    }

    @And("employee {string} is added to the activity {string}")
    public void employee_added_to_activity(String empId, String activityName) {
        Employee e = employeeMap.computeIfAbsent(empId, id -> new Employee(id, 0));
        Activity a = activityMap.get(activityName);
        try {
            a.addEmployeeToActivity(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @And("{double} hours are logged by {string} on {string}")
    public void hours_logged_by_employee(double hours, String empId, String activityName) {
        Employee e = employeeMap.get(empId);
        Activity a = activityMap.get(activityName);
        e.logWork(a, hours);
    }

    @When("the project leader generates a project report")
    public void the_project_leader_generates_a_project_report() throws Exception {
        report = project.getProjectReport();
    }

    @Then("the report contains {string}")
    public void the_report_contains(String expectedContent) {
        String normalizedReport = report.replaceAll("[\\s\\u00A0]+", " ").trim();
        String normalizedExpected = expectedContent.replaceAll("[\\s\\u00A0]+", " ").trim();
        assertTrue(normalizedReport.contains(normalizedExpected),
                "Expected report to contain: " + expectedContent + "\n\nActual report:\n" + report);
    }
}
