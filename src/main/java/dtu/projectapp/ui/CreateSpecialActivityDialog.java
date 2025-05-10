package dtu.projectapp.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateSpecialActivityDialog extends Dialog<ButtonType> {
    private TextField nameField;
    private TextField startDateField;
    private TextField endDateField;
    private TextField priorityField;

    public CreateSpecialActivityDialog(Stage owner) {
        setTitle("Create Special Activity (Sick Leave, Vacation, etc.)");
        initOwner(owner);

        // Create input fields
        nameField = new TextField();
        startDateField = new TextField("2025-05-12");
        endDateField = new TextField("2025-05-12");
        priorityField = new TextField();

        // Create a grid to layout the fields
        GridPane grid = new GridPane();
        grid.add(new Label("Activity Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Start Date (YYYY-MM-DD):"), 0, 1);
        grid.add(startDateField, 1, 1);
        grid.add(new Label("End Date (YYYY-MM-DD):"), 0, 2);
        grid.add(endDateField, 1, 2);
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

    public String getStartDate() {
        return startDateField.getText();
    }

    public String getEndDate() {
        return endDateField.getText();
    }

    public int getActivityHours() {
        try {
            return Integer.parseInt(priorityField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }
}

