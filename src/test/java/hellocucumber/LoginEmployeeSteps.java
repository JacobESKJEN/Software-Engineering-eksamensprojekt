package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginEmployeeSteps {    //Jacob
    ProjectApp projectApp;
    ErrorMessageHolder errorMessageHolder;

    public LoginEmployeeSteps(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @When("the user logs in with id {string}")
    public void theUserLogsInWithId(String string1) {
        try {
            projectApp.login(string1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the current logged in user has id {string}")
    public void theCurrentLoggedInUserHasId(String string) {
        assertEquals(string, projectApp.getLoggedInEmployeeId());
    }

    @Given("the user logged in with id {string}")
    public void theUserLoggedInWithId(String string) {
        theUserLogsInWithId(string);
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        try {
            projectApp.logout();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("there is no current logged in user")
    public void thereIsNoCurrentLoggedInUser() {
        assertEquals(projectApp.getLoggedInEmployee(), null);
    }

    @When("the user adds employee with id {string}")
    public void theUserAddsEmployeeWithId(String string) {
        try {
            projectApp.addEmployee(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(string);
        }

    }

    @Then("the employee with id {string} is in the list of employees")
    public void theEmployeeWithIdIsInTheListOfEmployees(String string) {
        boolean containsEmployee = false;
        for (Employee employee : projectApp.getEmployees()) {
            if (employee.getId().equals(string)) {
                containsEmployee = true;
                break;
            }
        }

        assertTrue(containsEmployee);
    }
}
