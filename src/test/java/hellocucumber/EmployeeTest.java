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

}
