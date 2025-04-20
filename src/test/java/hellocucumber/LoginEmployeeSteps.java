package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginEmployeeSteps {
    ProjectApp projectApp;
    ErrorMessageHolder errorMessageHolder;

    public LoginEmployeeSteps(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @When("the user logs in with id {string} and password {string}")
    public void theUserLogsInWithIdAndPassword(String string1, String string2) {
        try {
            projectApp.login(string1, string2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the current logged in user has id {string}")
    public void theCurrentLoggedInUserHasId(String string) {
        assertEquals(string, projectApp.getLoggedInEmployeeId());
    }
}
