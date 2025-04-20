package dtu.projectapp.model;

import java.util.List;
import java.util.ArrayList;

public class Employee {
    private String id;
    private String credential;
    private int activitiesAssigned;

    // List to store an employee
    private static List<Employee> EmployeeList = new ArrayList<>();

    public Employee(String id, String credential, int activitiesAssigned) {
        this.id = id;
        this.credential = credential;
        this.activitiesAssigned = activitiesAssigned;
    }

    public String getId() {
        return id;
    }

    public String getCredential() {
        return credential;
    }

    public int getActivitiesAssigned() {
        return activitiesAssigned;
    }

    // Adds an employee to the list
    public static void addEmployee(String id, String credential, int activitiesAssigned) {
        EmployeeList.add(new Employee(id, credential, activitiesAssigned));
    }

}
