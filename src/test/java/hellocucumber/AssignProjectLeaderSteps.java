package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignProjectLeaderSteps {
    ProjectApp projectApp;
    ErrorMessageHolder errorMessageHolder;
    Employee emp;

    public AssignProjectLeaderSteps(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @When("the employee with id {string} assigns the employee with id {string} as project leader of the project {string}")
    public void theEmployeeWithIdAssignsTheEmployeeWithIdAsProjectLeaderOfTheProject(String string1, String string2,
            String string3) {
        emp = projectApp.findEmployee("" + string1);
        Employee emp2 = projectApp.findEmployee("" + string2);
        Project project = projectApp.findProject(string3);
        try {
            project.setProjectLeader(emp, emp2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project leader of {string} has id {string}")
    public void theProjectLeaderOfHasId(String string1, String string2) {
        assertEquals(projectApp.findProject(string1).getProjectLeader().getId(), string2);
    }

    @Given("the project {string} has a project leader with id {string}")
    public void theProjectHasAProjectLeaderWithId(String string1, String string2) {
        theEmployeeWithIdAssignsTheEmployeeWithIdAsProjectLeaderOfTheProject(string2, string2, string1);
    }

}
