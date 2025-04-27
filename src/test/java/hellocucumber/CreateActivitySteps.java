package hellocucumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivitySteps {
    private Project project;
    private String errorMessage;

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
