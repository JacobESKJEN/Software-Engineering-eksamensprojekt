package hellocucumber;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;

public class CreateProjectSteps {
    ProjectApp projectApp;
    ErrorMessageHolder errorMessageHolder;
    Employee emp;

    public CreateProjectSteps(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("that there exists no project")
    public void thatThereExistsNoProject() {
        projectApp.setProjects(new ArrayList<Project>());
    }

    @Given("there is an employee with id {string}")
    public void thereIsAnEmployeeWithId(String string) {
        emp = new Employee(string, "", 0);
        projectApp.addEmployee(emp);
    }

    @Given("there is an employee with id {string} and password {string}")
    public void thereIsAnEmployeeWithId(String string1, String pass) {
        emp = new Employee(string1, pass, 0);
        projectApp.addEmployee(emp);
    }

    @When("an employee creates a new project with name {string}")
    public void anEmployeeCreatesANewProjectWithName(String string) {
        try {
            projectApp.createProject(emp, string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("there is a project with name {string}")
    public void thereIsAProjectWithName(String string) {
        boolean containsProject = false;
        for (Project project : projectApp.getProjects()) {
            if (project.getName().equals(string)) {
                containsProject = true;
            }
        }

        assertTrue(containsProject);
    }

    @Given("that there exists a project with name {string}")
    public void thatThereExistsAProjectWithName(String string) {
        anEmployeeCreatesANewProjectWithName(string);
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String string) {
        assertEquals(errorMessageHolder.getErrorMessage(), string);
    }
}
