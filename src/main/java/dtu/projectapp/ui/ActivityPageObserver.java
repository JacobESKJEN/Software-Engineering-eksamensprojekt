package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ActivityPageObserver implements PropertyChangeListener {
    private ActivityPage ActivityPage;

    public ActivityPageObserver(ActivityPage ActivityPage) {
        this.ActivityPage = ActivityPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("New Employee")) {
            System.out.println(evt.getNewValue());
            // ActivityPage.updateActivityEmployees((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("Remove Employee")) {
            System.out.println(evt.getNewValue());
            // ActivityPage.updateActivityEmployees((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("Update Time")) {
            System.out.println(evt.getNewValue());
            // ActivityPage.updateActivityTime((String) evt.getNewValue());
        }
    }

}
