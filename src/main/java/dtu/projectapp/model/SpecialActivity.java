package dtu.projectapp.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class SpecialActivity extends Activity {
    private LocalDate startDate;
    private LocalDate endDate;

    public SpecialActivity(String name, LocalDate startDate, LocalDate endDate, double hours) {
        super(name,
            startDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()),
            endDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()),
            startDate.getYear(),
            endDate.getYear(),
            hours);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    
}

   

    

