package dtu.projectapp.ui.View;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Activity;
import dtu.projectapp.ui.Observers.ActivityPageObserver;
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

    public Label getActivityNameLabel() { // Jacob
        return activityNameLabel;
    }

    public Label getBudgetedHoursLabel() { // Jacob
        return budgetedHoursLabel;
    }

    public Label getDeadlineLabel() { // Jacob
        return deadlineLabel;
    }

    public Button getChangeNameButton() { // Jacob
        return changeNameButton;
    }

    public Button getChangeEndDateButton() { // Jacob
        return changeEndDateButton;
    }

    public Button getChangeBudgetedHoursButton() { // Jacob
        return changeBudgetedHoursButton;
    }

    public Button getAssignedEmployeeButton() { // Jacob
        return assignedEmployeesButton;
    }

    public Button getHomePageButton() { // Jacob
        return homePageButton;
    }

    public Button getAvailableEmployeesButton() { // Jacob
        return availableEmployeesButton;
    }

    @Override
    public Scene getScene() { // Noah
        return scene;
    }

    @Override
    public PropertyChangeListener getObserver() { // Noah
        return observer;
    }

    public void setActivity(Activity activity) { // Noah
        observer.setActivity(activity);
    }

    public Button getAddEmployeeButton() { // Noah
        return addEmployeeButton;
    }

    public Button getRemoveEmployeeButton() { // Noah
        return removeEmployeeButton;
    }

    public ButtonBase getLogWorkButton() { // Oliver
        return logWorkButton;
    }

}
