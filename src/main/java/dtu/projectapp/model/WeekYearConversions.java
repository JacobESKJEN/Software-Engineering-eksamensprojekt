package dtu.projectapp.model;

public class WeekYearConversions { // Jacob og Noah
    public static int totalWeeks(int week, int year) {
        return year * 53 + week;
    }

    public static int calculateWeeksBetween(int startWeek, int endWeek, int startYear, int endYear) {
        // Calculate the number of weeks between the start and end weeks
        int startTotalWeeks = totalWeeks(startWeek, startYear);
        int endTotalWeeks = totalWeeks(endWeek, endYear);
        return endTotalWeeks - startTotalWeeks;
    }
}
