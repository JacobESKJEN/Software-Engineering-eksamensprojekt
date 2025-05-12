package dtu.projectapp.ui.Observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import dtu.projectapp.model.Project;
import dtu.projectapp.ui.View.HomePage;

public class HomePageObserver implements PropertyChangeListener { // Jacob
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
