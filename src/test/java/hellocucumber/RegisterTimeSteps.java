package hellocucumber;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.When;

public class RegisterTimeSteps {
    private Project project;
    private String errorMessage;
    private Activity activity;
    private Employee employee;
    private ProjectApp projectApp;
    private ErrorMessageHolder errorMessageHolder;

    public RegisterTimeSteps(ProjectApp projectApp, ErrorMessageHolder errorMessageHolder) {
        this.projectApp = projectApp;
        this.errorMessageHolder = errorMessageHolder;
    }

    // @Given("signed-in as Employee")
    //     public void signedInAsEmployee() {
            
    // }

    @When("the employee registers {int} hours to the activity {string}")
    public void theEmployeeRegistersHoursToTheActivity(Integer int1, String string) {
        //project = ProjectApp.findProject("Test Projec");
        activity = project.findActivity(string);
        employee.logWork(activity, int1);
    }

 

 

 
    

    

    

}