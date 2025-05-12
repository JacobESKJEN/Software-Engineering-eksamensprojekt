package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;
import dtu.projectapp.ui.ChangeActivityDialogs.ChangeBudgetedHoursDialog;
import dtu.projectapp.ui.ChangeActivityDialogs.ChangeEndDateDialog;
import dtu.projectapp.ui.ChangeActivityDialogs.ChangeNameDialog;
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
        activity.addObserver(getObserver());
        activityPage.setActivity(activity);

        activityPage.getActivityNameLabel().setText(activity.getName());
        activityPage.getDeadlineLabel()
                .setText("Deadline: week " + activity.getEndWeek() + " year " + activity.getEndYear());
        activityPage.getBudgetedHoursLabel()
                .setText("Budgeted hours: " + activity.getHoursWorked() + "/" + activity.getBudgetedTime());

        activityPage.getHomePageButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                app.newPage(new HomePageController(projectApp, app));
            }
        });

        activityPage.getChangeNameButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                ChangeNameDialog changeNameDialog = new ChangeNameDialog();
                String newName = changeNameDialog.getResult();
                if (!newName.equals("")) {
                    try {
                        project.changeActivityName(activity, newName);
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        activityPage.getChangeEndDateButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                ChangeEndDateDialog changeEndDateDialog = new ChangeEndDateDialog();
                changeEndDateDialog.showAndWait();
                if (changeEndDateDialog.getResult() == ButtonType.OK) {
                    try {
                        activity.setEndDate(Integer.parseInt(changeEndDateDialog.getEndWeek()),
                                Integer.parseInt(changeEndDateDialog.getEndYear()));
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        activityPage.getChangeBudgetedHoursButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                ChangeBudgetedHoursDialog changeBudgetedHoursDialog = new ChangeBudgetedHoursDialog();
                String budgetedHours = changeBudgetedHoursDialog.getResult();
                if (!budgetedHours.equals("")) {
                    try {
                        activity.setBudgetedTime(Double.parseDouble(budgetedHours));
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        activityPage.getAssignedEmployeeButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                ViewAssignedEmployeesDialog viewAssignedEmployeesDialog = new ViewAssignedEmployeesDialog();
                viewAssignedEmployeesDialog.updateList(activity.getEmployees());
                viewAssignedEmployeesDialog.showAndWait();
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

        activityPage.getAvailableEmployeesButton().setOnAction(new EventHandler<ActionEvent>() { // Jacob
            @Override
            public void handle(ActionEvent evt) {
                AvailableEmployeesDialog availableEmployeesDialog = new AvailableEmployeesDialog();
                availableEmployeesDialog
                        .updateList(projectApp.getAvailableEmployees(activity));
                availableEmployeesDialog.showAndWait();
            }
        });
        // if the activity is a special activity, disable the log work button
        if (project.isActivitySpecial(activityName)) {
            activityPage.getLogWorkButton().setDisable(true);
            activityPage.getChangeBudgetedHoursButton().setDisable(true);
        } else {
            activityPage.getLogWorkButton().setDisable(false);
            activityPage.getChangeBudgetedHoursButton().setDisable(false);
        }
        activityPage.getLogWorkButton().setOnAction(evt -> { // Oliver
            LogWorkDialog logWorkDialog = new LogWorkDialog((Stage) activityPage.getScene().getWindow());
            logWorkDialog.showAndWait();

            if (logWorkDialog.getResult() == ButtonType.OK) {
                try {
                    String employeeId = logWorkDialog.getEmployeeId();
                    double hours = logWorkDialog.getEmployeeHours();

                    if (hours < 0 || (hours * 2) % 1 != 0) {
                        throw new IllegalArgumentException(
                                "Only whole hours with 30 minute increments allowed (e.g. 1.0, 1.5, 2.0)");
                    }

                    Employee employee = projectApp.findEmployee(employeeId);

                    employee.logWork(activity, hours);
                } catch (Exception e) {
                    ErrorDialog.showExceptionDialog(e);
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
