package dtu.projectapp.model;

import javax.swing.tree.ExpandVetoException;

public class Project {
    private String name = "";
    private Employee projectLeader;

    public Project(String name) {
        this.name = name;
    }

    public void setProjectLeader(Employee setterEmployee, Employee projLead) throws Exception {
        if (projectLeader != null && !(projectLeader.equals(setterEmployee))) {
            throw new Exception("Project already has a leader");
        }
        projectLeader = projLead;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public String getName() {
        return name;
    }


    
    public void getEmployeeStatus(){
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        //project name
        System.err.println(getName());

        //project leader
        System.err.println(getProjectLeader());

        




    }

    public void getProjectETA(){
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }

        //Activity.loggedHours()     (for all activities)
        //Activity.remainingHours()
        //Activity.loggedHoursTotal()
        //Activity.remainingHoursTotal()



    }

    /**
     * @details This is the total report. This is a combination of getEmplyeeStatus() and getProjectETA()
     */
    public void getProjectReport(){
        if (getProjectLeader() == null) {
            throw new Exception("Project Has No Project Leader!");
        }
        getEmployeeStatus();
        getProjectETA();
    }

}
