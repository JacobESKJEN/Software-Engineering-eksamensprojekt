package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
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
        // activityPage = new ActivityPage(activity);
        this.app = app;
        this.projectApp = projectApp;
        activityPage = new ActivityPage();
        Activity activity = project.findActivity(activityName);

        activityPage.getHomePageButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                app.newPage(new HomePageController(projectApp, app));
            }
        });

        // add employee to activity button
        activityPage.getAddEmployeeButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent evt) {
                AssignEmployeeDialog assignEmployeeDialog = new AssignEmployeeDialog(
                        (Stage) activityPage.getScene().getWindow());
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

        // remove employee from activity button
        activityPage.getRemoveEmployeeButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent evt) {
                AssignEmployeeDialog assignEmployeeDialog = new AssignEmployeeDialog(
                        (Stage) activityPage.getScene().getWindow());
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

        activityPage.getLogWorkButton().setOnAction(evt -> {
        LogWorkDialog logWorkDialog = new LogWorkDialog((Stage) activityPage.getScene().getWindow());
        logWorkDialog.showAndWait();

        if (logWorkDialog.getResult() == ButtonType.OK) {
            try {
                String employeeId = logWorkDialog.getEmployeeId();
                double hours = logWorkDialog.getEmployeeHours();

                Employee employee = projectApp.findEmployee(employeeId);

                employee.logWork(activity, hours);
                System.out.println("Logged " + hours + " hours for " + employee.getId());
                System.out.println(employee.getHoursWorkedPerActivity());
            } catch (Exception e) {
                ErrorDialog.showExceptionDialog(e);
            }
        }});

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
