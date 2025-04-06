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

    @When("the employee with id {int} assigns the employee with id {int} as project leader of the project {string}")
    public void theEmployeeWithIdAssignsTheEmployeeWithIdAsProjectLeaderOfTheProject(Integer int1, Integer int2,
            String string) {
        emp = projectApp.findEmployee("" + int1);
        Employee emp2 = projectApp.findEmployee("" + int2);
        Project project = projectApp.findProject(string);
        try {
            project.setProjectLeader(emp, emp2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project leader of {string} has id {int}")
    public void theProjectLeaderOfHasId(String string, Integer int1) {
        assertEquals(projectApp.findProject(string).getProjectLeader().getId(), "" + int1);
    }

    @Given("the project {string} has a project leader with id {int}")
    public void theProjectHasAProjectLeaderWithId(String string, Integer int1) {
        theEmployeeWithIdAssignsTheEmployeeWithIdAsProjectLeaderOfTheProject(int1, int1, string);
    }

}
