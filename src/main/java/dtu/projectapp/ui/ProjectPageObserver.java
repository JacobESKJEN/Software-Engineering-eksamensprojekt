package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import dtu.projectapp.model.Activity;

public class ProjectPageObserver implements PropertyChangeListener {
    private ProjectPage projectPage;

    public ProjectPageObserver(ProjectPage projectPage) {
        this.projectPage = projectPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("New project leader")) {
            System.out.println(evt.getNewValue());
            projectPage.updateProjectLeader((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("New activity")) {
            List<Activity> updatedActivities = (List<Activity>) evt.getNewValue();
            projectPage.updateActivitys(updatedActivities); // Update the ListView
        }
        if (evt.getPropertyName().equals("Remove activity")) {
            List<Activity> updatedActivities = (List<Activity>) evt.getNewValue();
            
            projectPage.updateActivitys(updatedActivities); // Update the ListView
        }
    }
}
