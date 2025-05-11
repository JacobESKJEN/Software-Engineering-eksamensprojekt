package hellocucumber;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WhiteboxTests {

    @Test
    public void testRemoveEmployee() throws Exception {
        // Test Case A: employees = {}; employee = new Employee("Alice")
        Activity activity = new Activity("Test Activity", 1, 2, 2024, 2025, 10);
        Employee Alex = new Employee("Alex");

        // Assert that an exception is thrown when trying to remove an employee not in the list
        Exception exceptionA = assertThrows(Exception.class, () -> {
            activity.removeEmployee(Alex);
        });
        assertEquals("No such employee assigned to activity", exceptionA.getMessage());

        // Test Case B: employees = {emp}; employee = emp = new Employee("Bob")
        Employee bob = new Employee("Bob");
        activity.getEmployees().add(bob); // Add Bob to the activity

        // Assert that no exception is thrown when removing an employee in the list
        assertDoesNotThrow(() -> {
            activity.removeEmployee(bob);
        });

        // Assert that Bob is no longer in the employees list
        assertFalse(activity.getEmployees().contains(bob));
    }
}
