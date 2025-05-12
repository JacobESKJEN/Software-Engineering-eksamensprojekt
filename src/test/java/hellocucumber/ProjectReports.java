package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.ProjectApp;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.Activity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectReports {   //Oliver

    private String projectName = "";
    private String report;
    private ProjectApp projectApp;
    private ErrorMessageHolder errorMessageHolder;

    public ProjectReports(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("signed-in as Project leader with id {string}")
    public void signed_in_as_project_leader_with_id(String id) throws Exception {
        projectApp.addEmployee(id);
        //projectName = "test";
        projectApp.createProject(projectName);
        Project project = projectApp.findProject(projectName);
        Employee leader = projectApp.findEmployee(id);
        project.setProjectLeader(leader, leader);
    }

    @Given("there exists a project with name {string}")
    public void there_exists_a_project_with_name(String name) throws Exception{
        projectName = name;
        if (projectApp.findProject(projectName) == null) {
            projectApp.createProject(name);
        }

        Project project = projectApp.findProject(projectName);
        Employee leader = projectApp.getEmployees().get(0);
        project.setProjectLeader(leader, leader);
    }

    @Given("the project {string} has activities {string} and {string}")
    public void the_project_has_activities(String projectName, String activity1, String activity2) {
        try {
            projectApp.createActivity(projectName, activity1, 20, 21, 2025, 2025, 10);
            projectApp.createActivity(projectName, activity2, 20, 21, 2025, 2025, 10);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("employee {string} is added to the activity {string}")
    public void employee_added_to_activity(String empId, String activityName) {
        try{
            if(projectApp.findEmployee(empId) == null){
                projectApp.addEmployee(empId);
            }

            Employee employee = projectApp.findEmployee(empId);
            Activity activity = null;
            for (Project p : projectApp.getProjects()){
                activity = p.findActivity(activityName);
                if(activity != null){
                    break;
                }
            }
            if(activity != null){
                activity.addEmployeeToActivity(employee);
            }
        } catch (Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("{double} hours are logged by {string} on {string}")
    public void hours_logged_by_employee(double hours, String empId, String activityName) {
        try{
            Employee employee = projectApp.findEmployee(empId);
            Activity activity = null;
            for(Project p : projectApp.getProjects()){
                activity = p.findActivity(activityName);
                if(activity != null){
                    break;
                }
            }
            if(employee != null && activity != null){
                employee.logWork(activity, hours);
            }
        }catch(Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader generates a project report")
    public void the_project_leader_generates_a_project_report() throws Exception {
        Project project = projectApp.findProject(projectName);
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
