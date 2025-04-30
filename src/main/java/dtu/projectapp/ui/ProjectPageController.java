package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import dtu.projectapp.model.*;

public class ProjectPageController implements PageController {
    private ProjectPage projectPage;
    private ProjectApp projectApp;
    private App app;
    private AssignProjectLeaderDialog assignProjectLeaderDialog;
    private ProjectStatusDialog projectStatusDialog;

    public ProjectPageController(ProjectApp projectApp, App app, Project project) {
        projectPage = new ProjectPage(project);
        this.app = app;
        this.projectApp = projectApp;

        project.addObserver(projectPage.getObserver());

        assignProjectLeaderDialog = new AssignProjectLeaderDialog();
        projectStatusDialog = new ProjectStatusDialog();

        projectPage.getSetProjectLeaderButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                String projectLeader = assignProjectLeaderDialog.getResult();
                if (!projectLeader.equals("")) {
                    try {
                        project.setProjectLeader(projectApp.getLoggedInEmployee(),
                                projectApp.findEmployee(projectLeader));
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        projectPage.getProjectStatusButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                try {
                    String projectStatus = project.getProjectReport();
                    projectStatusDialog.resetDialog(projectStatus);
                    projectStatusDialog.showDialog();
                } catch (Exception e) {
                    ErrorDialog.showExceptionDialog(e);
                }
            }
        });
    }

    public Scene getScene() {
        return projectPage.getScene();
    }

    public PropertyChangeListener getObserver() {
        return projectPage.getObserver();
    }
}
