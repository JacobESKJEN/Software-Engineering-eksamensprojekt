package hellocucumber;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.ProjectApp;

class ProjectAppTest {
    @Test
    void testFindActivityInExistingProject() throws Exception {
        ProjectApp projectApp = new ProjectApp();
        projectApp.createProject("Project1");
        projectApp.createActivity("Project1", "Activity1", 10, 12, 2025, 2025, 40.0);
        Activity activity = projectApp.findActivity("Project1", "Activity1");

        assertNotNull(activity, "The activity should be found in the project.");
        assertEquals("Activity1", activity.getName(), "The activity name should match.");
    }

    @Test
    void testFindActivityInNonExistingProject() {
        ProjectApp projectApp = new ProjectApp();
        Activity activity = projectApp.findActivity("NonExistingProject", "Activity1");
        assertNull(activity, "The activity should not be found as the project does not exist.");
    }

    @Test
    void testFindNonExistingActivityInExistingProject() throws Exception {
        ProjectApp projectApp = new ProjectApp();
        projectApp.createProject("Project1");
        Activity activity = projectApp.findActivity("Project1", "NonExistingActivity");
        assertNull(activity, "The activity should not be found as it does not exist in the project.");
    }
}
