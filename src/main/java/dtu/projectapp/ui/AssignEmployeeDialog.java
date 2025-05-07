package dtu.projectapp.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AssignEmployeeDialog extends Dialog<ButtonType> {
    private TextField employeeField;

    public AssignEmployeeDialog(Stage owner) {
        setTitle("Assign Employee");
        initOwner(owner);

        // Create input field
        employeeField = new TextField();

        // Create a grid to layout the field
        GridPane grid = new GridPane();
        grid.add(new Label("Employee Name:"), 0, 0);
        grid.add(employeeField, 1, 0);

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

    public String getEmployeeName() {
        return employeeField.getText();
    }
}
