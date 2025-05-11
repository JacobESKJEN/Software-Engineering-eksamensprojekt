package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterTimeSteps {
    private Project project;
    private String errorMessage;
    private Activity activity;
    private Employee employee;
    private ProjectApp projectApp;
    private ErrorMessageHolder errorMessageHolder;

    public RegisterTimeSteps(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }


    @Given("a project has been created")
    public void aProjectCreated() throws Exception{
        projectApp.createProject("Test");
        project = projectApp.findProject("Test");
    }

    @Given("there exists an activity with the name {string}, start date {string}, end date {string}, and budgeted time {int}")
    public void activityExists(String name, String startDate, String endDate, Integer budget) throws Exception{
        projectApp.createActivity(project.getName(), name, 19, 20, 2025, 2025, budget);
        activity = project.findActivity(name);
    }

    @Given("there exists an employee with id {string}")
    public void employeeExists(String id){
        try{
            projectApp.addEmployee(id);
            employee = projectApp.findEmployee(id);
        } catch(Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee registers {double} hours to the activity {string}")
    public void empRegHoursAct(Double hours, String activityName){
        project = projectApp.findProject("Test");
        activity = project.findActivity(activityName);
        try{
            employee.logWork(activity, hours);
        } catch(Exception e){
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity's total logged hours is {double}")
    public void activityTotalHours(Double remainingHOurs){
        double actual = activity.getHoursWorked();
        assertEquals(remainingHOurs, actual, 0.01, "Activity's logged hours did not match");
    }

    @Then("the employee has logged {double} hours to the activity")
    public void theEmployeeHasLoggedHoursToTheActivity(Double expectedHours) {
    double actual = employee.getHoursWorkedPerActivity().getOrDefault(activity, 0.0);
    assertEquals(expectedHours, actual, 0.01, "Employee's logged hours for the activity did not match");
}
}