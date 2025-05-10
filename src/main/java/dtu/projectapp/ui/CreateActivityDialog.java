package dtu.projectapp.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateActivityDialog extends Dialog<ButtonType> {
    private TextField nameField;
    private TextField startDateField;
    private TextField endDateField;
    private TextField priorityField;
    private TextField startYearField;
    private TextField endYearField;
    public CreateActivityDialog(Stage owner) {
        setTitle("Create Activity");
        initOwner(owner);

        // Create input fields
        nameField = new TextField();
        startDateField = new TextField("0");
        endDateField = new TextField("1");
        startYearField = new TextField("2025");
        endYearField = new TextField("2025");
        priorityField = new TextField("0");

        // Create a grid to layout the fields
        GridPane grid = new GridPane();
        grid.add(new Label("Activity Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label(""), 0, 1);
        grid.add(startDateField, 1, 1);
        grid.add(new Label(""), 0, 2);
        grid.add(endDateField, 1, 2);
        grid.add(new Label("Start Date(week)(year):"), 0, 1);
        grid.add(startYearField, 2, 1);
        grid.add(new Label("End Date:(week)(year)"), 0, 2);
        grid.add(endYearField, 2, 2);
        grid.add(new Label("Hours (Integer):"), 0, 3);
        grid.add(priorityField, 1, 3);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Handle the result
        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return ButtonType.OK;
            }
            return null;
        });
    }

    public String getActivityName() {
        return nameField.getText();
    }

    public int getStartDate() {
        try {
            return Integer.parseInt(startDateField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }

    public int getEndDate() {
        try {
            return Integer.parseInt(endDateField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }
    public int getStartYear() {
        try {
            return Integer.parseInt(startYearField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }
    public int getEndYear() {
        try {
            return Integer.parseInt(endYearField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }

    public double getActivityHours() {
        try {
            return Integer.parseInt(priorityField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }
}

