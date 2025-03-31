package hellocucumber;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;

public class createProjectSteps {
    ProjectApp projectApp = new ProjectApp();

    @Given("that there exists no project")
    public void thatThereExistsNoProject() {
        // Write code here that turns the phrase above into concrete actions
        projectApp.setProjects(new ArrayList<Project>());
    }

    @Given("there is an employee with id {int}")
    public void thereIsAnEmployeeWithId(int int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("an employee creates a new project with name {string}")
    public void anEmployeeCreatesANewProjectWithName(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there is a project with name {string}")
    public void thereIsAProjectWithName(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
