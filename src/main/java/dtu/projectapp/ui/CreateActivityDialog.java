package dtu.projectapp.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateActivityDialog extends Dialog<ButtonType> {
    private TextField nameField;
    private TextField startField;
    private TextField endField;
    private TextField priorityField;

    public CreateActivityDialog(Stage owner) {
        setTitle("Create Activity");
        initOwner(owner);

        // Create input fields
        nameField = new TextField();
        startField = new TextField();
        endField = new TextField();
        priorityField = new TextField();

        // Create a grid to layout the fields
        GridPane grid = new GridPane();
        grid.add(new Label("Activity Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Start Date (YYYY-MM-DD):"), 0, 1);
        grid.add(startField, 1, 1);
        grid.add(new Label("End Date (YYYY-MM-DD):"), 0, 2);
        grid.add(endField, 1, 2);
        grid.add(new Label("Priority (Integer):"), 0, 3);
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
        return startField.getText();
    }

    public String getEndDate() {
        return endField.getText();
    }

    public int getActivityHours() {
        try {
            return Integer.parseInt(priorityField.getText());
        } catch (NumberFormatException e) {
            return -1; // or handle it as needed
        }
    }
}

