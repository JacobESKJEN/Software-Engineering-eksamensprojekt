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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    
}
