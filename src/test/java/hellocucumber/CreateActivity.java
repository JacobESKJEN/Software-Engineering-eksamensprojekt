package hellocucumber;

import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateActivity {
    private Project project;
    private String errorMessage;
    private Activity activity;
    private Employee employee;

    @Given("a project exists")
    public void a_project_exists() {
        project = new Project("Test Project");
    }

    @Then("the activity is created and added to the project")
    public void activity_is_created_and_added() {
        assertEquals(1, project.getActivities().size());
        assertEquals("Requirements Specification", project.getActivities().get(0).getName());
    }

    @When("I create a new activity with the name {string}, start date {string}, end date {string}, and budgeted time {int}")
    public void iCreateANewActivityWithTheNameStartDateEndDateAndBudgetedTime(String string, String string2,
            String string3, int int1) {
        try {
            activity = new Activity(
                    string,
                    LocalDate.parse(string2),
                    LocalDate.parse(string3),
                    int1);
            project.addActivity(activity);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I receive an error message {string}")
    public void i_receive_error_message(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }
    
    @Given("signed-in as Project leader")
    public void signedInAsProjectLeader() {
        employee = new Employee("ProjectLeader", "yy", 0);
        try {
            project.setProjectLeader(employee, employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Given("the activity {string} has {int} members")
    public void theActivityHasMembers(String string, Integer int1) {
        assertEquals(string, activity.getName());
        assertEquals(int1, activity.getEmployeesAmount());
    }
    @Then("the activity {string} has {int} member")
        public void theActivityHasMember(String string, Integer int1) {
            assertEquals(string, activity.getName());
            System.out.println(activity.getName());
            assertEquals(int1, activity.getEmployeesAmount()); 
    }
    @When("the project leader adds employee with {string} to the activity")
    public void theProjectLeaderAddsEmployeeWithToTheActivity(String string) {
        Employee employee = new Employee(string, "yy",0);
        activity.addEmployeeToActivity(employee);
    }
}
