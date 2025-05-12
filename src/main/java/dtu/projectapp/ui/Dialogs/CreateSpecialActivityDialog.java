package dtu.projectapp.ui.Dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateSpecialActivityDialog extends Dialog<ButtonType> { // Noah
    private ComboBox<String> typeComboBox;
    private TextField startDateField;
    private TextField endDateField;
    private TextField employeeField;

    public CreateSpecialActivityDialog(Stage owner) {
        setTitle("Create Special Activity (Sick Leave, Vacation, etc.)");
        initOwner(owner);

        // Create input fields
        typeComboBox = new ComboBox<String>();
        startDateField = new TextField("2025-05-12");
        endDateField = new TextField("2025-05-12");
        employeeField = new TextField();

        typeComboBox.getItems().addAll("Sick leave", "Vacation", "Course");

        // Create a grid to layout the fields
        GridPane grid = new GridPane();
        grid.add(new Label("Activity Name:"), 0, 0);
        grid.add(typeComboBox, 1, 0);
        grid.add(new Label("Start Date (YYYY-MM-DD):"), 0, 1);
        grid.add(startDateField, 1, 1);
        grid.add(new Label("End Date (YYYY-MM-DD):"), 0, 2);
        grid.add(endDateField, 1, 2);
        grid.add(new Label("Employee id:"), 0, 3);
        grid.add(employeeField, 1, 3);

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

    public ComboBox<String> getComboBox() { // Jacob
        return typeComboBox;
    }

    public String getActivityName() {
        return typeComboBox.getValue();
    }

    public String getStartDate() {
        return startDateField.getText();
    }

    public String getEndDate() {
        return endDateField.getText();
    }

    public String getAssignedEmployee() { // Jacob
        return employeeField.getText();
    }
}
