package dtu.projectapp.ui.Observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dtu.projectapp.ui.View.LogInPage;

public class LogInPageObserver implements PropertyChangeListener { // Jacob
    private LogInPage logInPage;

    public LogInPageObserver(LogInPage logInPage) {
        this.logInPage = logInPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
