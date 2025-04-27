package dtu.projectapp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dtu.projectapp.model.ProjectApp;

public class ProjectPageObserver implements PropertyChangeListener {
    private ProjectPage projectPage;

    public ProjectPageObserver(ProjectPage projectPage) {
        this.projectPage = projectPage;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ProjectApp projectApp = (ProjectApp) evt.getSource();
        if (evt.getPropertyName().equals("ProjectLeaderChanged")) {
            projectPage.updateProjectLeader((String) evt.getNewValue());
        }
    }
}
