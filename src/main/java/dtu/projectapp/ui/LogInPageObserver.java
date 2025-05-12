package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogInPageObserver implements PropertyChangeListener { // Jacob
    private LogInPage logInPage;

    public LogInPageObserver(LogInPage logInPage) {
        this.logInPage = logInPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
