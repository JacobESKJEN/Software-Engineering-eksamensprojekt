package dtu.projectapp.ui.Observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import dtu.projectapp.model.Activity;
import dtu.projectapp.ui.View.ProjectPage;

public class ProjectPageObserver implements PropertyChangeListener {
    private ProjectPage projectPage;

    public ProjectPageObserver(ProjectPage projectPage) {
        this.projectPage = projectPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("New project leader")) { // Jacob
            System.out.println(evt.getNewValue());
            projectPage.updateProjectLeader((String) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("New activity")) { // Noah
            List<Activity> updatedActivities = (List<Activity>) evt.getNewValue();
            projectPage.updateActivitys(updatedActivities); // Update the ListView
        }
        if (evt.getPropertyName().equals("Remove activity")) { // Noah
            List<Activity> updatedActivities = (List<Activity>) evt.getNewValue();

            projectPage.updateActivitys(updatedActivities); // Update the ListView
        }
    }
}
