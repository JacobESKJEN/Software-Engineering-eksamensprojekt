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
        if (evt.getPropertyName().equals("New project leader")) {
            System.out.println(evt.getNewValue());
            projectPage.updateProjectLeader((String) evt.getNewValue());
        }
    }
}
