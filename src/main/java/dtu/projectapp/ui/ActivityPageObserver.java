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
        System.out.println(evt.getNewValue());
    }

}
