package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.ProjectApp;
import javafx.scene.Scene;

public class ActivityPageController implements PageController {
    private ActivityPage activityPage;
    private ProjectApp projectApp;
    private App app;
   
    public ActivityPageController(ProjectApp projectApp, App app, String activity) {
        //activityPage = new ActivityPage(activity);
        this.app = app;
        this.projectApp = projectApp;
        activityPage = new ActivityPage();
    }

    @Override
    public Scene getScene() {
        return activityPage.getScene();
    }

    @Override
    public PropertyChangeListener getObserver() {
        return activityPage.getObserver();
    }
}
