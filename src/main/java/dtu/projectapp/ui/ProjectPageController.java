package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
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

    public ProjectPageController(ProjectApp projectApp, App app, Project project) {
        projectPage = new ProjectPage(project);
        this.app = app;
        this.projectApp = projectApp;
        

        project.addObserver(projectPage.getObserver());
        
        assignProjectLeaderDialog = new AssignProjectLeaderDialog();
        projectStatusDialog = new ProjectStatusDialog();

        
        //creates the listVeiw of activities in the project on the right side of the page
        projectPage.getActivityListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            
            public void handle(MouseEvent evt) {
                Object selectedItem = projectPage.getActivityListView().getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ActivityPageController activityPage = new ActivityPageController(projectApp, app,
                            project.findActivityName(selectedItem.toString()), project);
                    app.newPage(activityPage);
                }
            }
        });
   
        //Button for creating a new activity
        projectPage.getAddActivityButton().setOnAction(event -> {
        CreateActivityDialog dialog = new CreateActivityDialog((Stage) projectPage.getScene().getWindow());
        dialog.showAndWait();
            // Creates 4 input-lines  for Name, StartDate, EndDate and BuggetHours activity
        if (dialog.getResult() == ButtonType.OK) {
            String activityName = dialog.getActivityName();
            String startDate = dialog.getStartDate();
            String endDate = dialog.getEndDate();
            int activityHours = dialog.getActivityHours();
                if (!activityName.equals("")) {
                    try {
                        
                        String projectName = project.getName();
                        projectApp.createActivity(projectName, activityName, startDate, endDate , activityHours);
                        System.err.println("Activity created");
                        
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        //remove activity button
        projectPage.getRemoveActivityButton().setOnAction(event -> {
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
            
        

        //makes input employee as project leader
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

        projectPage.getEmpStatusButton().setOnAction(new EventHandler<ActionEvent>() {
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

        projectPage.getETAReportButton().setOnAction(new EventHandler<ActionEvent>() {
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
