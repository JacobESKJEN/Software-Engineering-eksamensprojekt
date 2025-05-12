package hellocucumber;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FindAvailableEmployee {    //Jacob
    ProjectApp projectApp;
    ErrorMessageHolder errorMessageHolder;
    List<Employee> availableEmployees;

    public FindAvailableEmployee(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("the project {string} has {int} activities between week {int} of year {int} and week {int} of year {int}")
    public void theProjectHasActivities(String string, Integer int1, Integer int2, Integer int3, Integer int4,
            Integer int5) {
        Project project = projectApp.findProject(string);
        for (int i = 0; i < int1; i++) {
            try {
                Activity activity = new Activity("" + i, int2, int4, int3, int5, 100);
                project.addActivity(activity);
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }

        }
    }

    @When("a project leader checks for available employees between week {int} of year {int} and week {int} of year {int}")
    public void aProjectLeaderChecksForAvailableEmployeesBetweenWeekAndWeek(Integer int1, Integer int2, Integer int3,
            Integer int4) {
        availableEmployees = projectApp.getAvailableEmployees(int1, int3, int2, int4);
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

    @Then("the employee with id {string} is not in the list of available employees")
    public void theEmployeeWithIdIsNotInTheListOfAvailableEmployees(String string) {
        boolean containsEmployee = false;

        for (Employee employee : availableEmployees) {
            if (employee.getId().equals(string)) {
                containsEmployee = true;
                break;
            }
        }

        assertFalse(containsEmployee);
    }

    @When("a project leader checks for available employees for activity {string} with start week {int}, start year {int}, end week {int}, end year {int}, and budgeted time {int}")
    public void aProjectLeaderChecksForAvailableEmployeesForActivityWithStartDateEndDateAndBudgetedTime(String string,
            Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
        try {
            Activity activity = new Activity(string, int1, int3, int2, int4, int5);

            availableEmployees = projectApp.getAvailableEmployees(activity);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("a project leader checks for available employees for activity at index {int} for project {string}")
    public void aProjectLeaderChecksForAvailableEmployeesForActivityAtIndexForProject(Integer int1, String string) {
        Project project = projectApp.findProject(string);
        Activity activity = project.getActivities().get(int1);

        availableEmployees = projectApp.getAvailableEmployees(activity);
    }
}
