package hellocucumber;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import io.cucumber.java.en_old.Ac;

public class EmployeeTest {
    private Activity activity;

    @Test 
    public void testValidEmployeeId() throws Exception { //Alexander
        String validId = "1234";
        Employee employee = new Employee(validId);
        assertEquals(validId, employee.getId());
    }

    @Test
    public void testInvalidEmployeeIdThrowsException() { //Alexander
        String invalidId = "12345";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(invalidId);
        });
        assertEquals("Maximum length of employee id is 4", exception.getMessage());
    }

    @Test
    void testNoOverlap() throws Exception { //Alexander
        Employee employee = new Employee("1234");
        Activity activity = new Activity("Activity1", 10, 12, 2025, 2025, 40.0);
        employee.assignActivity(activity);

        boolean isAvailable = employee.isAvailableBetweenWeeks(15, 17, 2025, 2025);
        assertTrue(isAvailable, "Employee should be available as there is no overlap.");
    }





    @Test
    void testHoursWorkedPerActivity() throws Exception{    //Oliver
        Employee employee = new Employee("ID03");
        Activity activity = new Activity("Design", 11, 12, 2025, 2025, 100);

        employee.assignActivity(activity);
        employee.logWork(activity, 2.5);

        Map<Activity, Double> hoursMap = employee.getHoursWorkedPerActivity();

        assertTrue(hoursMap.containsKey(activity), "Activity from hoursMap");
        assertEquals(2.5, hoursMap.get(activity), 0.001, "Logged hours must match expected value");
    }
}
