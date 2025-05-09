package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import dtu.projectapp.model.*;

public class FindAvailableEmployee {
    ProjectApp projectApp;
    ErrorMessageHolder errorMessageHolder;
    List<Employee> availableEmployees;

    public FindAvailableEmployee(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("the project {string} has {int} activities")
    public void theProjectHasActivities(String string, Integer int1) {
        Project project = projectApp.findProject(string);
        for (int i = 0; i < int1; i++) {
            project.addActivity(
                    new Activity("" + i, LocalDate.parse("2024-04-20"), LocalDate.parse("2024-05-20"), 100));
        }
    }

    @When("a project leader checks for available employees between week {int} and week {int}")
    public void aProjectLeaderChecksForAvailableEmployeesBetweenWeekAndWeek(Integer int1, Integer int2) {
        availableEmployees = projectApp.getAvailableEmployees(int1, int2);
    }

    @Then("the employee with id {string} is in the list of available employees")
    public void theEmployeeWithIdIsInTheListOfAvailableEmployees(String string) {
        boolean containsEmployee = false;
        for (Employee employee : availableEmployees) {
            System.out.println("Employee:" + employee.getId());
            if (employee.getId().equals(string)) {
                containsEmployee = true;
                break;
            }
        }
        assertTrue(containsEmployee);
    }

    @Given("the employee with id {string} is assigned to all activies of project {string}")
    public void theEmployeeWithIdIsAssignedToAllActiviesOfProject(String string, String string2) {
        Employee employee = projectApp.findEmployee(string);
        for (Activity activity : projectApp.findProject(string2).getActivities()) {
            try {
                activity.addEmployeeToActivity(employee);
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    }

    @Then("the list of available employees is empty")
    public void theListOfAvailableEmployeesIsEmpty() {
        assertTrue(availableEmployees.isEmpty());
    }
}
