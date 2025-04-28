package dtu.projectapp.model;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee {
    private String id;
    private String credential;
    private int activitiesAssigned;

    // List to store an employee
    private static List<Employee> EmployeeList = new ArrayList<>();
    private Map<Activity, Double> hoursPerActivity = new HashMap<>();

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

    public void logWork(Activity activity, double hours){                                   // tracks hours worked per activity
        hoursPerActivity.put(activity, hoursPerActivity.getOrDefault(activity, 0.0)+hours);         // the Map is updated with the activity as a key and the hours worked as a value
    }

    public double getTotalWork(){
        double total = 0;
        for (double hours: hoursPerActivity.values()){
            total += hours;
        }
        return total;
    }

    public static List<Employee> getEmployees(){
        return EmployeeList;
    }
}
