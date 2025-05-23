package dtu.projectapp.ui.Controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Project;

import dtu.projectapp.model.ProjectApp;
import dtu.projectapp.ui.App;
import dtu.projectapp.ui.Dialogs.AssignEmployeeDialog;
import dtu.projectapp.ui.Dialogs.AssignProjectLeaderDialog;
import dtu.projectapp.ui.Dialogs.CreateActivityDialog;
import dtu.projectapp.ui.Dialogs.CreateSpecialActivityDialog;
import dtu.projectapp.ui.Dialogs.ErrorDialog;
import dtu.projectapp.ui.Dialogs.ProjectStatusDialog;
import dtu.projectapp.ui.Dialogs.RemoveActivityDialog;
import dtu.projectapp.ui.View.ProjectPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProjectPageController implements PageController {
    private ProjectPage projectPage;
    private ProjectApp projectApp;
    private App app;

    private AssignProjectLeaderDialog assignProjectLeaderDialog;
    private AssignEmployeeDialog assignEmployeeDialog;
    private ProjectStatusDialog projectStatusDialog;
    private CreateActivityDialog CreateActivityDialog;

    private void reportButtons(Project project) { // Oliver
        boolean currentLeader = project.getProjectLeader() != null
                && project.getProjectLeader().equals(projectApp.getLoggedInEmployee());
        projectPage.getETAReportButton().setDisable(!currentLeader);
        projectPage.getEmpStatusButton().setDisable(!currentLeader);
        projectPage.getProjectStatusButton().setDisable(!currentLeader);
    }

    public ProjectPageController(ProjectApp projectApp, App app, Project project) {
        projectPage = new ProjectPage(project);
        this.app = app;
        this.projectApp = projectApp;

        project.addObserver(projectPage.getObserver());

        assignProjectLeaderDialog = new AssignProjectLeaderDialog();
        projectStatusDialog = new ProjectStatusDialog();

        projectPage.updateActivitys(project.getActivities());

        projectPage.getHomePageButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                app.newPage(new HomePageController(projectApp, app));
            }
        });
        // click on activity in list view to open activity page
        projectPage.getActivityListView().setOnMouseClicked(new EventHandler<MouseEvent>() { // Noah
            @Override

            public void handle(MouseEvent evt) {
                Object selectedItem = projectPage.getActivityListView().getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ActivityPageController activityPage = new ActivityPageController(projectApp, app,
                            project.findActivity(selectedItem.toString()).getName(), project);
                    app.newPage(activityPage);
                }
            }
        });
        // create activity button
        projectPage.getAddActivityButton().setOnAction(event -> { // Noah
            CreateActivityDialog dialog = new CreateActivityDialog((Stage) projectPage.getScene().getWindow());
            dialog.showAndWait();

            if (dialog.getResult() == ButtonType.OK) {
                String activityName = dialog.getActivityName();
                int startDate = dialog.getStartDate();
                int endDate = dialog.getEndDate();
                int startYear = dialog.getStartYear();
                int endYear = dialog.getEndYear();
                if (!activityName.equals("")) {
                    try {
                        double activityHours = dialog.getActivityHours();
                        String projectName = project.getName();
                        projectApp.createActivity(projectName, activityName, startDate, endDate, startYear, endYear,
                                activityHours);

                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });
        // create special activity button //sick leave, vacation, etc.
        projectPage.getAddSpecialActivityButton().setOnAction(event -> { // Noah
            CreateSpecialActivityDialog dialog = new CreateSpecialActivityDialog(
                    (Stage) projectPage.getScene().getWindow());
            dialog.showAndWait();
            if (dialog.getResult() == ButtonType.OK) {
                String activityName = dialog.getActivityName();
                String startDate = dialog.getStartDate();
                String endDate = dialog.getEndDate();
                String activityEmployee = dialog.getAssignedEmployee();
                if (activityName != null && activityEmployee != null && !activityName.equals("")
                        && !activityEmployee.equals("")) {
                    try {
                        String projectName = project.getName();
                        projectApp.createSpecialActivity(projectName,
                                activityName + " (" + projectApp.findEmployee(activityEmployee).getId() + ")",
                                startDate, endDate,
                                projectApp.findEmployee(activityEmployee));
                        System.err.println("Activity created");
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        // remove activity button
        projectPage.getRemoveActivityButton().setOnAction(event -> { // Noah
            RemoveActivityDialog dialog = new RemoveActivityDialog((Stage) projectPage.getScene().getWindow());
            dialog.showAndWait();
            if (dialog.getResult() == ButtonType.OK) {
                String activityName = dialog.getActivityName();
                if (!activityName.equals("")) {
                    try {

                        String projectName = project.getName();
                        projectApp.RemoveActivity(projectName, activityName);
                        System.err.println("Remove activity");

                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }

            }
        });

        // set project leader button
        projectPage.getSetProjectLeaderButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
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

        project.addObserver(new PropertyChangeListener() { // Oliver
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if ("New project leader".equals(event.getPropertyName())) {
                    reportButtons(project);
                }
            }
        });

        reportButtons(project);

        projectPage.getProjectStatusButton().setOnAction(new EventHandler<ActionEvent>() { // Oliver
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

        projectPage.getEmpStatusButton().setOnAction(new EventHandler<ActionEvent>() {// Oliver
            @Override
            public void handle(ActionEvent evt) {
                try {
                    String empStatus = project.getEmployeeStatus();
                    projectStatusDialog.resetDialog(empStatus);
                    projectStatusDialog.showDialog();
                } catch (Exception e) {
                    ErrorDialog.showExceptionDialog(e);
                }
            }
        });

        projectPage.getETAReportButton().setOnAction(new EventHandler<ActionEvent>() {// Oliver
            @Override
            public void handle(ActionEvent evt) {
                try {
                    String etaReport = project.getProjectETA();
                    projectStatusDialog.resetDialog(etaReport);
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
