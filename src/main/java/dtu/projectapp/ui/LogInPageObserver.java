package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dtu.projectapp.model.ProjectApp;

public class LogInPageObserver implements PropertyChangeListener {
    private LogInPage logInPage;

    public LogInPageObserver(LogInPage logInPage) {
        this.logInPage = logInPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
