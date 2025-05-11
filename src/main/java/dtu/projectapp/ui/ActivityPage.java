package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Activity;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ActivityPage implements Page {
    private Scene scene;
    private BorderPane root;
    private Button homePageButton;

    private Label activityNameLabel;
    private Label budgetedHoursLabel;
    private Label deadlineLabel;

    private Button assignedEmployeesButton;
    private Button availableEmployeesButton;
    private Button addEmployeeButton;
    private Button removeEmployeeButton;
    private Button logWorkButton;

    private Button changeNameButton;
    private Button changeBudgetedHoursButton;
    private Button changeEndDateButton;
    VBox vbox = new VBox();
    private ActivityPageObserver observer = new ActivityPageObserver(this);

    public ActivityPage() {
        root = new BorderPane();
        scene = new Scene(root);

        homePageButton = new Button("Return to home page");
        HBox topHbox = new HBox();
        topHbox.getChildren().add(homePageButton);
        root.setTop(topHbox);

        activityNameLabel = new Label("Name");
        budgetedHoursLabel = new Label("Hours remaining: ");
        deadlineLabel = new Label("Activity deadline: ");

        changeBudgetedHoursButton = new Button("Change budgeted hours");
        changeNameButton = new Button("Change activity name");
        changeEndDateButton = new Button("Change activity deadline");

        assignedEmployeesButton = new Button("View assigned employees");
        addEmployeeButton = new Button("Add Employee");
        removeEmployeeButton = new Button("Remove Employee");
        availableEmployeesButton = new Button("Find available employees");
        logWorkButton = new Button("Log Work");
        HBox hboxReports = new HBox();
        hboxReports.setAlignment(Pos.BASELINE_LEFT);
        hboxReports.getChildren().addAll(addEmployeeButton, removeEmployeeButton, availableEmployeesButton,
                logWorkButton, assignedEmployeesButton);

        HBox hboxChanges = new HBox();
        hboxChanges.setAlignment(Pos.BASELINE_LEFT);
        hboxChanges.getChildren().addAll(changeNameButton, changeBudgetedHoursButton, changeEndDateButton);

        vbox.getChildren().addAll(hboxReports, hboxChanges, activityNameLabel, budgetedHoursLabel, deadlineLabel);

        root.setCenter(vbox);
    }

    public Label getActivityNameLabel() {
        return activityNameLabel;
    }

    public Label getBudgetedHoursLabel() {
        return budgetedHoursLabel;
    }

    public Label getDeadlineLabel() {
        return deadlineLabel;
    }

    public Button getChangeNameButton() {
        return changeNameButton;
    }

    public Button getChangeEndDateButton() {
        return changeEndDateButton;
    }

    public Button getChangeBudgetedHoursButton() {
        return changeBudgetedHoursButton;
    }

    public Button getAssignedEmployeeButton() {
        return assignedEmployeesButton;
    }

    public Button getHomePageButton() {
        return homePageButton;
    }

    public Button getAvailableEmployeesButton() {
        return availableEmployeesButton;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public PropertyChangeListener getObserver() {
        return observer;
    }

    public void setActivity(Activity activity) {
        observer.setActivity(activity);
    }

    public Button getAddEmployeeButton() {
        return addEmployeeButton;
    }

    public Button getRemoveEmployeeButton() {
        return removeEmployeeButton;
    }

    public ButtonBase getLogWorkButton() {
        return logWorkButton;
    }

}
