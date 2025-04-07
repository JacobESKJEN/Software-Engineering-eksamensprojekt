package dtu.projectapp.model;

import java.util.List;
import java.util.ArrayList;

public class Employee {
    private String id;
    private String credential;

    // List to store an employee
    private static List<Employee> EmployeeList = new ArrayList<>();

    public Employee(String id, String credential) {
        this.id = id;
        this.credential = credential;
    }

    public String getId() {
        return id;
    }

    public String getCredential(){
        return credential;
    }

    // Adds an employee to the list
    public static void addEmployee (String id, String credential){
        EmployeeList.add(new Employee(id, credential));
    }
    
    


}
