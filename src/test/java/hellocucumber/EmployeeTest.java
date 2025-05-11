package hellocucumber;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;

public class EmployeeTest {
    private Activity activity;

    @Test
    public void testValidEmployeeId() throws Exception {
        String validId = "1234";
        Employee employee = new Employee(validId);
        assertEquals(validId, employee.getId());
    }

    @Test
    public void testInvalidEmployeeIdThrowsException() {
        String invalidId = "12345";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(invalidId);
        });
        assertEquals("Maximum length of employee id is 4", exception.getMessage());
    }

    @Test
    void testNoOverlap() throws Exception {
        Employee employee = new Employee("1234");
        Activity activity = new Activity("Activity1", 10, 12, 2025, 2025, 40.0);
        employee.assignActivity(activity);

        boolean isAvailable = employee.isAvailableBetweenWeeks(15, 17, 2025, 2025);
        assertTrue(isAvailable, "Employee should be available as there is no overlap.");
    }

    @Test
    void testGetTotalWork() throws Exception {
        Employee employee = new Employee("1234");
        Activity activity1 = new Activity("Activity1", 10, 12, 2025, 2025, 40.0);
        Activity activity2 = new Activity("Activity2", 15, 17, 2025, 2025, 30.0);

        employee.assignActivity(activity1);
        employee.assignActivity(activity2);

        employee.logWork(activity1, 5.0);
        employee.logWork(activity2, 7.5);

        double totalWork = employee.getTotalWork();
        assertEquals(12.5, totalWork, 0.001, "The total work should be the sum of hours logged for all activities.");
    }

    @Test
    void testGetTotalWorkWithNoActivities() throws Exception {
        Employee employee = new Employee("1234");
        double totalWork = employee.getTotalWork();
        assertEquals(0.0, totalWork, "The total work should be 0 when no activities are assigned.");
    }

    @Test
    void testGetTotalWorkWithNoLoggedHours() throws Exception {
        Employee employee = new Employee("1234");
        Activity activity1 = new Activity("Activity1", 10, 12, 2025, 2025, 40.0);
    
        employee.assignActivity(activity1);
        double totalWork = employee.getTotalWork();
        assertEquals(0.0, totalWork, "The total work should be 0 when no hours are logged for activities.");
    }
}
