package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dtu.projectapp.model.ProjectApp;

public class HomePageObserver implements PropertyChangeListener {
    private HomePage homePage;

    public HomePageObserver(HomePage homePage) {
        this.homePage = homePage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ProjectApp projectApp = (ProjectApp) evt.getSource();
        if (evt.getPropertyName().equals("Projects")) {
            homePage.updateProjects(projectApp.getProjects());
        }
    }
}
