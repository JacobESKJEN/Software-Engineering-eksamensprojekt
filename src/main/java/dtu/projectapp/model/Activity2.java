package dtu.projectapp.model;


public class Activity2 {
    private String name;
    private int startWeek; // Week number for the start of the activity
    private int endWeek;   // Week number for the end of the activity
    private int year;      // Year for the activity
    private int hours;     // Amount of hours for the activity

    public Activity2(String name, int startWeek, int endWeek, int year, int hours) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.year = year;
        this.hours = hours;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getYear() {
        return year;
    }

    public int getHours() {
        return hours;
    }

    // Additional methods as needed
}

   

   

    

