package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ActivityPageController implements PageController {
    private ActivityPage activityPage;
    private ProjectApp projectApp;
    private AssignEmployeeDialog assignEmployeeDialog;
    private App app;
   
    public ActivityPageController(ProjectApp projectApp, App app, String activityName, Project project) {
        //activityPage = new ActivityPage(activity);
        this.app = app;
        this.projectApp = projectApp;
        activityPage = new ActivityPage();
        Activity activity = project.findActivity(activityName);
       
        //add employee to activity button
        activityPage.getAddEmployeeButton().setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent evt) {
                AssignEmployeeDialog assignEmployeeDialog = new AssignEmployeeDialog((Stage) activityPage.getScene().getWindow());
                assignEmployeeDialog.showAndWait();

                if (assignEmployeeDialog.getResult() == ButtonType.OK) {
                    String employeeName = assignEmployeeDialog.getEmployeeName(); 
                    if (!employeeName.isEmpty()) {
                        try {
                            // Add the employee to the activity
                            activity.addEmployeeToActivity(projectApp.findEmployee(employeeName));
                        } catch (Exception e) {
                            ErrorDialog.showExceptionDialog(e);
                        }
                    }
                }
            }
        });


        //remove employee from activity button
        activityPage.getRemoveEmployeeButton().setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent evt) {
                AssignEmployeeDialog assignEmployeeDialog = new AssignEmployeeDialog((Stage) activityPage.getScene().getWindow());
                assignEmployeeDialog.showAndWait();

                if (assignEmployeeDialog.getResult() == ButtonType.OK) {
                    String employeeName = assignEmployeeDialog.getEmployeeName(); 
                    if (!employeeName.isEmpty()) {
                        try {
                            // Remove the employee from the activity
                            activity.removeEmployee(projectApp.findEmployee(employeeName));
                        } catch (Exception e) {
                            ErrorDialog.showExceptionDialog(e);
                        }
                    }
                }
            }
        });

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
