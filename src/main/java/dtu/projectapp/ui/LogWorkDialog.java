package dtu.projectapp.ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LogWorkDialog extends Dialog<ButtonType> {
    private TextField employeeIdInput;
    private TextField hoursInput;

    public LogWorkDialog(Stage parentStage) {
        setTitle("Log Work");
        setHeaderText("Enter employee ID and hours worked");

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        employeeIdInput = new TextField();
        employeeIdInput.setPromptText("Employee ID");

        hoursInput = new TextField();
        hoursInput.setPromptText("e.g. 2.5");

        grid.add(new Label("Employee ID:"), 0, 0);
        grid.add(employeeIdInput, 1, 0);
        grid.add(new Label("Hours:"), 0, 1);
        grid.add(hoursInput, 1, 1);

        getDialogPane().setContent(grid);
        initOwner(parentStage);
    }

    public String getEmployeeId() {
        return employeeIdInput.getText().trim();
    }

    public double getEmployeeHours() throws NumberFormatException {
        return Double.parseDouble(hoursInput.getText().trim());
    }
}
