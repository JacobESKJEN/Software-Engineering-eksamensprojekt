package dtu.projectapp.model;

public class WeekYearConversions {
    public static int totalWeeks(int week, int year) { // Jacob
        return year * 53 + week;
    }

    public static int calculateWeeksBetween(int startWeek, int endWeek, int startYear, int endYear) { // Noah
        // Calculate the number of weeks between the start and end weeks
        int startTotalWeeks = totalWeeks(startWeek, startYear);
        int endTotalWeeks = totalWeeks(endWeek, endYear);
        return endTotalWeeks - startTotalWeeks;
    }
}
