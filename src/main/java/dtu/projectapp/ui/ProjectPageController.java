package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class ProjectPageController implements PageController {
    private ProjectPage projectPage;
    private ProjectApp projectApp;
    private App app;
    
    private AssignProjectLeaderDialog assignProjectLeaderDialog;
    private ProjectStatusDialog projectStatusDialog;
    private CreateActivityDialog CreateActivityDialog;

    public ProjectPageController(ProjectApp projectApp, App app, Project project) {
        projectPage = new ProjectPage(project);
        this.app = app;
        this.projectApp = projectApp;

        project.addObserver(projectPage.getObserver());
        
        assignProjectLeaderDialog = new AssignProjectLeaderDialog();
        projectStatusDialog = new ProjectStatusDialog();

        CreateActivityDialog = new CreateActivityDialog();
   
        projectPage.getAddActivityButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
         
            public void handle(ActionEvent event) {
                // String ActivityStartDate = (CreateActivityDialog.getResult());
                // String ActivityEndDate = (CreateActivityDialog.getResult());
                ////change this from localdate to string because of an error
                
                String ActivityName = CreateActivityDialog.getResult();
                // LocalDate ActivityStartDate = LocalDate.parse(CreateActivityDialog.getResult()); 
                // LocalDate ActivityEndDate = LocalDate.parse(CreateActivityDialog.getResult());
                // double BudgetedHours =  Double.parseDouble(CreateActivityDialog.getResult());

                //String date = "2005-9-12";
                if (!ActivityName.equals("")) {
                    try {
                        //String ActivityStartDateString = ActivityStartDate.toString();
                        //String ActivityEndDateString = ActivityEndDate.toString();
                        //projectApp.createActivity(projectname, ActivityName, "2005-09-12", "2005-10-12", 1);
                        String projectName = project.getName();
                        projectApp.createActivity(projectName, ActivityName, "2005-09-12", "2005-10-12", 1);
                        System.err.println("Activity created");
                        
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
                
                
            }
        });
       
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
