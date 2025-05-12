package dtu.projectapp.ui.Dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RemoveActivityDialog extends Dialog<ButtonType> { // Noah
    private TextField nameField;

    public RemoveActivityDialog(Stage owner) {
        setTitle("Remove Activity");
        initOwner(owner);

        // Create input fields
        nameField = new TextField();

        // Create a grid to layout the fields
        GridPane grid = new GridPane();
        grid.add(new Label("Activity Name:"), 0, 0);
        grid.add(nameField, 1, 0);

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

}
