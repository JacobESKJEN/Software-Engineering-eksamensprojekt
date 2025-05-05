package hellocucumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivity {
    private Project project;
    private String errorMessage;
    private Activity activity;
    private Employee employee;
    private ProjectApp projectApp;
    private ErrorMessageHolder errorMessageHolder;

    public CreateActivity(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a project exists")
    public void a_project_exists() {
        project = new Project("Test Project", "20251");
    }

    @Then("the activity is created and added to the project")
    public void activity_is_created_and_added() {
        assertTrue(project.getActivities().contains(activity));
    }

    @Given("there exists an activity with the name {string}, start date {string}, end date {string}, and budgeted time {int}")
    public void thereExistsAnActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, String string2,
            String string3, Integer int1) {
        iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(string, string2, string3, 0);
    }

    @When("I try to find an activity with the name {string}")
    public void iTryToFindAnActivityWithTheName(String string) {
        activity = project.findActivity(string);
    }

    @Then("the activity has name {string}")
    public void theActivityHasName(String string) {
        assertEquals(string, activity.getName());
    }

    @When("I create a new activity with the name {string}, start date {string}, end date {string}, and budgeted time {int}")
    public void iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, String string2,
            String string3, int int1) {
        try {
            activity = new Activity(string,
                    LocalDate.parse(string2),
                    LocalDate.parse(string3),
                    int1);
            project.addActivity(activity);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("signed-in as Project leader")
    public void signedInAsProjectLeader() {
        employee = new Employee("ProjectLeader", 0);
        try {
            project.setProjectLeader(employee, employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the activity {string} has no members")
    public void theActivityHasMembers(String string) {
        activity = project.findActivity(string);
        activity.getEmployees().clear();
    }

    @Then("the activity {string} has {int} member")
    public void theActivityHasMember(String string, Integer int1) {
        activity = project.findActivity(string);
        assertEquals(int1, activity.getEmployeesAmount());
    }

    @When("the project leader adds employee with id {string} to the activity {string}")
    public void theProjectLeaderAddsEmployeeWithToTheActivity(String string, String string2) {
        activity = project.findActivity(string2);
        System.out.println(project.getActivities().size());
        ;
        System.out.println(activity.getName());
        employee = projectApp.findEmployee(string);
        try {
            activity.addEmployeeToActivity(employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader removes employee with {string} from the activity")
    public void theProjectLeaderRemovesEmployeeWithFromTheActivity(String string) {
        employee = projectApp.findEmployee(string);
        try {
            activity.removeEmployee(employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader updates the time estimate of {string} to budgeted time of {int}")
    public void theProjectLeaderUpdatesTheTimeEstimateOfToBudgetedTimeOf(String string, Integer int1) {
        Activity activity = project.findActivity(string);
        try {
            activity.setBudgetedTime(int1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system reflects the new estimate: of {double}")
    public void theSystemReflectsTheNewEstimateOf(Double double1) {
        assertEquals(double1, activity.getBudgetedTime());
    }
}