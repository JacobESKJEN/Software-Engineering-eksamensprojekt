package hellocucumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Given("a project exists") //Alexander
    public void a_project_exists() {
        try {
            projectApp.createProject("Test Project");
            project = projectApp.findProject("Test Project");
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the activity is created and added to the project") //Alexander
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

    @Given("there exists an activity with the name {string}, start week {int}, end week {int}, start year {int}, end year {int}, and budgeted time {double}") //Alexander
    public void thereExistsAnActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, int int1, int int2,
            int int3, int int4, double double1) {
        iCreateANewActivityWithTheNameStartWeekEndWeekStartYearEndYearAndBudgetedTime(string, int1, int2, int3, int4, double1);
    }

    @When("I try to find an activity with the name {string}") //Alexander
    public void iTryToFindAnActivityWithTheName(String string) {
        activity = project.findActivity(string);
    }

    @Then("the activity has name {string}") //Alexander
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

    // @When("I create a new activity with the name {string}, start week {int}, end week {int}, start year {int}, end year {int}, and budgeted time {int}")
    // public void iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, int int1, int int2,
    //         int int3, int int4, int int5) {
    //     try {
    //         project.createActivity(string, int1, int2, int3, int4, int5);
    //         activity = project.findActivity(string);
    //     } catch (Exception e) {
    //         errorMessageHolder.setErrorMessage(e.getMessage());
    //     }
    // }

    @When("I create a new activity with the name {string}, start week {int}, end week {int}, start year {int}, end year {int}, and budgeted time {double}") //Alexander
    public void iCreateANewActivityWithTheNameStartWeekEndWeekStartYearEndYearAndBudgetedTime(String string,
            Integer int1, Integer int2, Integer int3, Integer int4, Double double1) {
                try {
            project.createActivity(string, int1, int2, int3, int4, double1);
            activity = project.findActivity(string);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Given("signed-in as Project leader") //Alexander
    public void signedInAsProjectLeader() {
        try {
            employee = new Employee("PjLd");
            project.setProjectLeader(employee, employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("the activity {string} has no members") //Alexander
    public void theActivityHasMembers(String string) {
        activity = project.findActivity(string);
        activity.getEmployees().clear();
    }

    @Then("the activity {string} has {int} member") //Alexander
    public void theActivityHasMember(String string, Integer int1) {
        activity = project.findActivity(string);
        assertEquals(int1, activity.getEmployeesAmount());
    }

    @When("the project leader adds employee with id {string} to the activity {string}") //Alexander
    public void theProjectLeaderAddsEmployeeWithToTheActivity(String string, String string2) {
        activity = project.findActivity(string2);
        employee = projectApp.findEmployee(string);
        try {
            activity.addEmployeeToActivity(employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader removes employee with {string} from the activity") //Alexander
    public void theProjectLeaderRemovesEmployeeWithFromTheActivity(String string) {
        employee = projectApp.findEmployee(string);
        try {
            activity.removeEmployee(employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @When("the project leader updates the time estimate of {string} to budgeted time of {int}") //Alexander
    public void theProjectLeaderUpdatesTheTimeEstimateOfToBudgetedTimeOf(String string, Integer int1) {
        Activity activity = project.findActivity(string);
        try {
            activity.setBudgetedTime(int1);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system reflects the new estimate: of {double}") //Alexander
    public void theSystemReflectsTheNewEstimateOf(Double double1) {
        assertEquals(double1, activity.getBudgetedTime());
    }

    @When("the project leader renames {string} to {string}") //Alexander
    public void theProjectLeaderRenamesTo(String string, String string2) {
        activity = project.findActivity(string);
        try {
            project.changeActivityName(activity, string2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system updates the activity name") //Alexander
    public void theSystemUpdatesTheActivityName() {
        assertEquals("UI Programming", activity.getName());
    }

    @When("the project leader extends the deadline to week {int} of year {int}") //Alexander
    public void theProjectLeaderExtendsTheDeadlineToNew(int int1, int int2) {
        try {
            activity.setEndDate(int1, int2);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system updates the activity deadline to week {int} of year {int}") //Alexander
    public void theSystemUpdatesTheActivityDeadline(int int1, int int2) {
        assertEquals(int1, activity.getEndWeek());
        assertEquals(int2, activity.getEndYear());
    }

    @Given("there is no activity named {string}") //Alexander
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

    @When("the project leader attempts to edit {string}") //Alexander
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

    @When("the project leader removes the activity {string}") //Alexander
    public void theProjectLeaderRemovesTheActivity(String string) {
        activity = project.findActivity(string);
        try {
            if (activity == null) {
                throw new Exception("Activity not found");
            }
            projectApp.RemoveActivity(project.getName(),activity.getName());
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system confirms the activity {string} has been removed") //Alexander
    public void theSystemConfirmsTheActivityHasBeenRemoved(String string) {
        assertTrue(project.findActivity(string) == null);
    }

    @When("the project leader checks the weeks till completion of {string}") //Alexander
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

    @Then("the system returns the number of weeks till completion: {int} weeks") //Alexander
    public void theSystemReturnsTheNumberOfWeeksTillCompletionWeeks(Integer int1) {
        assertEquals(int1, (int) weeksBetween);
    }

    @Then("the employee with id {string} has the activity {string} in their activity list") //Alexander
    public void theEmployeeWithIdHasTheActivityInTheirActivityList(String string, String string2) {
        Employee employee = projectApp.findEmployee(string);
        Activity activity = project.findActivity(string2);
        assertTrue(employee.getActivities().contains(activity));
    }

    @Then("the employee with id {string} does not have the activity {string} in their activity list") //Alexander
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

    @When("the project leader creates a special activity named {string} with start date {string} and end date {string} for employee {string}") //Alexander
    public void theProjectLeaderCreatesASpecialActivity(String activityName, String startDate, String endDate, String employeeId){
        try {
            Employee employee = projectApp.findEmployee(employeeId);
            project.createSpecialActivity(activityName, startDate, endDate, employee);
            projectApp.createSpecialActivity(employeeId, activityName, startDate, endDate, employee);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the special activity {string} has start date {string} and end date {string}") //Alexander
    public void theSpecialActivityHasStartDateAndEndDate(String activityName, String startDate, String endDate) {
        SpecialActivity activity = (SpecialActivity) project.findActivity(activityName);
        assertEquals(LocalDate.parse(startDate), activity.getStartDate());
        assertEquals(LocalDate.parse(endDate), activity.getEndDate());
    }

    @Then("the special activity is assigned to employee {string}") //Alexander
    public void theSpecialActivityIsAssignedToEmployee(String employeeId) {
        assertTrue(activity.getEmployees().stream().anyMatch(e -> e.getId().equals(employeeId)));
    }

    @When("the project leader retrieves the details of the special activity {string}") //Alexander
    public void theProjectLeaderRetrievesTheDetailsOfTheSpecialActivity(String activityName) {
        activity = project.findActivity(activityName); // Retrieve the activity by name
        assertNotNull(activity, "The special activity was not found in the project."); // Ensure the activity exists
        assertTrue(activity instanceof SpecialActivity, "The activity is not a SpecialActivity."); // Ensure it's a special activity
    }

    @Then("the activity {string} is created and added to the project") //Alexander
    public void theActivityIsCreatedAndAddedToTheProject(String string) {
        Activity activity = project.findActivity(string);
        System.out.println(project.getActivities());
        assertTrue(activity != null);
    }

    @Given("there exists a special activity with the name {string}, start date {string}, and end date {string}") //Alexander
    public void thereExistsASpecialActivity(String activityName, String startDate, String endDate) throws Exception {
        Employee employee = new Employee("1"); 
        project.createSpecialActivity(activityName, startDate, endDate, employee); 
    }

    @When("I check if the activity {string} is special") //Alexander
    public void iCheckIfTheActivityIsSpecial(String activityName) {
        activity = project.findActivity(activityName); 
        assertNotNull(activity, "The activity was not found in the project."); 
        boolean isSpecial = project.isActivitySpecial(activityName); 
        assertTrue(isSpecial, "The activity should be identified as special."); 
    }

    @Then("the result should be true") //Alexander
    public void theResultShouldBeTrue() {
        assertTrue(project.isActivitySpecial(activity.getName()), "The activity should be identified as special.");
    }
}
