package dtu.projectapp.ui.Observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Activity;
import dtu.projectapp.ui.View.ActivityPage;

public class ActivityPageObserver implements PropertyChangeListener {
    private ActivityPage activityPage;
    private Activity activity;

    public ActivityPageObserver(ActivityPage activityPage) {
        this.activityPage = activityPage;
    }

    public void setActivity(Activity activity) { // Noah
        this.activity = activity;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { // Jacob
        if (evt.getPropertyName().equals("Name change")) {
            activityPage.getActivityNameLabel().setText(activity.getName());
        } else if (evt.getPropertyName().equals("End date change")) {
            activityPage.getDeadlineLabel()
                    .setText("Deadline: week " + activity.getEndWeek() + " year " + activity.getEndYear());
        } else if (evt.getPropertyName().equals("Update budgeted time")) {
            activityPage.getBudgetedHoursLabel()
                    .setText("Budgeted hours: " + activity.getHoursWorked() + "/" + activity.getBudgetedTime());
        }
    }

}
