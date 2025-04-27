package hellocucumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditActivitySteps {
    private Project project;
    private String errorMessage;
    

    @Given("there is a project named {string}")
    public void thereIsAProjectNamed(String string) {
        
    }

    @Given("the project {string} has {int} members")
    public void theProjectHasMembers(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the project leader adds employee with id {int} to the activity")
    public void theProjectLeaderAddsEmployeeWithIdToTheActivity(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the activity has {int} members")
    public void theActivityHasMembers(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }




    @Given("a project exists")
    public void a_project_exists() {
        project = new Project("Test Project");
    }

    @Then("the activity is created and added to the project")
    public void activity_is_created_and_added() {
        assertEquals(1, project.getActivities().size());
        assertEquals("Requirements Specification", project.getActivities().get(0).getName());
    }

    @When("I create a new activity with the name {string}, start date {string}, end date {string}, and budgeted time {int}")
    public void iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, String string2,
            String string3, int int1) {
        try {
            Activity activity = new Activity(
                    string,
                    LocalDate.parse(string2),
                    LocalDate.parse(string3),
                    int1);
            project.addActivity(activity);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I receive an error message {string}")
    public void i_receive_error_message(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }
}
