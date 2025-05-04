package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import dtu.projectapp.model.Project;

public class HomePageObserver implements PropertyChangeListener {
    private HomePage homePage;

    public HomePageObserver(HomePage homePage) {
        this.homePage = homePage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Update projects")) {
            List<Project> listOfProjects = (List<Project>) evt.getNewValue();
            homePage.updateProjects(listOfProjects);
        }
    }
}
