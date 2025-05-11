package hellocucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import dtu.projectapp.model.SpecialActivity;
import dtu.projectapp.model.WeekYearConversions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateActivity {
    private Project project;
    private Activity activity;
    private Employee employee;
    private ProjectApp projectApp;
    private ErrorMessageHolder errorMessageHolder;
    private long weeksBetween;

    public CreateActivity(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a project exists")
    public void a_project_exists() {
        project = new Project("Test Project", "20251");
    }

    @Then("the activity is created and added to the project")
    public void activity_is_created_and_added() {
        assertTrue(project.getActivities().contains(activity));
    }

    // @Given("there exists an activity with the name {string}, start date {string},
    // end date {string}, and budgeted time {int}")
    // public void
    // thereExistsAnActivityWithTheNameStartDateEndDateAndBudgetedTime(String
    // string, String string2,
    // String string3, Integer int1) {
    // iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(string,
    // string2, string3, 0);
    // }

    @Given("there exists an activity with the name {string}, start week {int}, end week {int}, start year {int}, end year {int}, and budgeted time {int}")
    public void thereExistsAnActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, int int1, int int2,
            int int3, int int4, int int5) {
        iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(string, int1, int2, int3, int4, int5);
    }

    @When("I try to find an activity with the name {string}")
    public void iTryToFindAnActivityWithTheName(String string) {
        activity = project.findActivity(string);
    }

    @Then("the activity has name {string}")
    public void theActivityHasName(String string) {
        assertEquals(string, activity.getName());
    }

    // @When("I create a new activity with the name {string}, start date {string},
    // end date {string}, and budgeted time {int}")
    // public void
    // iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(String string,
    // String string2,
    // String string3, int int1) {
    // try {
    // activity = new Activity(string,
    // LocalDate.parse(string2),
    // LocalDate.parse(string3),
    // int1);
    // project.addActivity(activity);
    // } catch (Exception e) {
    // errorMessageHolder.setErrorMessage(e.getMessage());
    // }
    // }

    @When("I create a new activity with the name {string}, start week {int}, end week {int}, start year {int}, end year {int}, and budgeted time {int}")
    public void iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, int int1, int int2,
            int int3, int int4, int int5) {
        try {
            activity = new Activity(string, int1, int2, int3, int4, int5);
            project.addActivity(activity);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("signed-in as Project leader")
    public void signedInAsProjectLeader() {
        try {
            employee = new Employee("PjLd");
            project.setProjectLeader(employee, employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the activity {string} has no members")
    public void theActivityHasMembers(String string) {
        activity = project.findActivity(string);
        activity.getEmployees().clear();
    }

    @Then("the activity {string} has {int} member")
    public void theActivityHasMember(String string, Integer int1) {
        activity = project.findActivity(string);
        assertEquals(int1, activity.getEmployeesAmount());
    }

    @When("the project leader adds employee with id {string} to the activity {string}")
    public void theProjectLeaderAddsEmployeeWithToTheActivity(String string,
            String string2) {
        activity = project.findActivity(string2);
        employee = projectApp.findEmployee(string);
        try {
            activity.addEmployeeToActivity(employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader removes employee with {string} from the activity")
    public void theProjectLeaderRemovesEmployeeWithFromTheActivity(String string) {
        employee = projectApp.findEmployee(string);
        try {
            activity.removeEmployee(employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader updates the time estimate of {string} to budgeted time of {int}")
    public void theProjectLeaderUpdatesTheTimeEstimateOfToBudgetedTimeOf(String string, Integer int1) {
        Activity activity = project.findActivity(string);
        try {
            activity.setBudgetedTime(int1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system reflects the new estimate: of {double}")
    public void theSystemReflectsTheNewEstimateOf(Double double1) {
        assertEquals(double1, activity.getBudgetedTime());
    }

    @When("the project leader renames {string} to {string}")
    public void theProjectLeaderRenamesTo(String string, String string2) {
        activity = project.findActivity(string);
        try {
            activity.setName(string2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system updates the activity name")
    public void theSystemUpdatesTheActivityName() {
        assertEquals("UI Programming", activity.getName());
    }

    @When("the project leader extends the deadline to week {int} of year {int}")
    public void theProjectLeaderExtendsTheDeadlineToNew(int int1, int int2) {
        try {
            activity.setEndDate(int1, int2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system updates the activity deadline to week {int} of year {int}")
    public void theSystemUpdatesTheActivityDeadline(int int1, int int2) {
        assertEquals(int1, activity.getEndWeek());
        assertEquals(int2, activity.getEndYear());
    }

    @Given("there is no activity named {string}")
    public void thereIsNoActivityNamed(String string) {
        activity = project.findActivity(string);
        if (activity != null) {
            try {
                project.removeActivity(activity.getName());
            } catch (Exception e) {
                errorMessageHolder.setErrorMessage(e.getMessage());
            }
        }
    }

    @When("the project leader attempts to edit {string}")
    public void theProjectLeaderAttemptsToEdit(String string) {
        activity = project.findActivity(string);
        try {
            if (activity == null) {
                throw new Exception("Activity not found");
            }
            activity.setName("Research");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader removes the activity {string}")
    public void theProjectLeaderRemovesTheActivity(String string) {
        activity = project.findActivity(string);
        try {
            if (activity == null) {
                throw new Exception("Activity not found");
            }
            project.removeActivity(activity.getName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system confirms the activity has been removed")
    public void theSystemConfirmsTheActivityHasBeenRemoved() {
        assertTrue(project.getActivities().isEmpty());

    }

    @When("the project leader checks the weeks till completion of {string}")
    public void theProjectLeaderChecksTheWeeksTillCompletionOf(String string) {
        Activity activity = project.findActivity(string);
        try {
            if (activity == null) {
                throw new Exception("Activity not found");
            }
            weeksBetween = WeekYearConversions.calculateWeeksBetween(activity.getStartWeek(),
                    activity.getEndWeek(), activity.getStartYear(), activity.getEndYear());
            System.out.println("Number of weeks till completion: " + weeksBetween);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system returns the number of weeks till completion: {int} weeks")
    public void theSystemReturnsTheNumberOfWeeksTillCompletionWeeks(Integer int1) {
        assertEquals(int1, (int) weeksBetween);
    }

    @Then("the employee with id {string} has the activity {string} in their activity list")
    public void theEmployeeWithIdHasTheActivityInTheirActivityList(String string, String string2) {
        Employee employee = projectApp.findEmployee(string);
        Activity activity = project.findActivity(string2);
        assertTrue(employee.getActivities().contains(activity));
    }

    @Then("the employee with id {string} does not have the activity {string} in their activity list")
    public void theEmployeeWithIdDoesNotHaveTheActivityInTheirActivityList(String string, String string2) {
        Employee employee = projectApp.findEmployee(string);
        boolean containsActivity = false;
        for (Activity activity : employee.getActivities()) {
            if (activity.getName().equals(string2)) {
                containsActivity = true;
                break;
            }
        }
        assertFalse(containsActivity);
    }

    @When("the project leader creates a special activity named {string} with start date {string} and end date {string} for employee {string}")
    public void theProjectLeaderCreatesASpecialActivity(String activityName, String startDate, String endDate,
            String employeeId) {
        try {
            Employee employee = projectApp.findEmployee(employeeId);
            project.createSpecialActivity(activityName, startDate, endDate, employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the special activity {string} has start date {string} and end date {string}")
    public void theSpecialActivityHasStartDateAndEndDate(String activityName, String startDate, String endDate) {
        SpecialActivity activity = (SpecialActivity) project.findActivity(activityName);
        assertEquals(LocalDate.parse(startDate), activity.getStartDate());
        assertEquals(LocalDate.parse(endDate), activity.getEndDate());
    }

    @Then("the special activity is assigned to employee {string}")
    public void theSpecialActivityIsAssignedToEmployee(String employeeId) {
        assertTrue(activity.getEmployees().stream().anyMatch(e -> e.getId().equals(employeeId)));
    }

    @When("the project leader retrieves the details of the special activity {string}")
    public void theProjectLeaderRetrievesTheDetailsOfTheSpecialActivity(String activityName) {
        activity = project.findActivity(activityName); // Retrieve the activity by name
        assertNotNull(activity, "The special activity was not found in the project."); // Ensure the activity exists
        assertTrue(activity instanceof SpecialActivity, "The activity is not a SpecialActivity."); // Ensure it's a special activity
    }

    @Then("the activity {string} is created and added to the project")
    public void theActivityIsCreatedAndAddedToTheProject(String string) {
        Activity activity = project.findActivity(string);
        System.out.println(project.getActivities());
        assertTrue(activity != null);
    }
}