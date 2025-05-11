package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ActivityPage implements Page {
    private Scene scene;
    private BorderPane root;
    private Button homePageButton;

    private Button assignedEmployeesButton;
    private Button availableEmployeesButton;
    private Button addEmployeeButton;
    private Button removeEmployeeButton;
    private Button logWorkButton;
    VBox vbox = new VBox();
    private ActivityPageObserver observer = new ActivityPageObserver(this);

    public ActivityPage() {
        root = new BorderPane();
        scene = new Scene(root);

        homePageButton = new Button("Return to home page");
        HBox topHbox = new HBox();
        topHbox.getChildren().add(homePageButton);
        root.setTop(topHbox);

        assignedEmployeesButton = new Button("View assigned employees");
        addEmployeeButton = new Button("Add Employee");
        removeEmployeeButton = new Button("Remove Employee");
        availableEmployeesButton = new Button("Find available employees");
        logWorkButton = new Button("Log Work");
        HBox hboxReports = new HBox();
        hboxReports.setAlignment(Pos.BASELINE_LEFT);
        hboxReports.getChildren().addAll(addEmployeeButton, removeEmployeeButton, availableEmployeesButton,
                logWorkButton, assignedEmployeesButton);

        vbox.getChildren().add(hboxReports);

        root.setCenter(vbox);
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
