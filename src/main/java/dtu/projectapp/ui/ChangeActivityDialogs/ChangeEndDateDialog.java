package dtu.projectapp.ui.ChangeActivityDialogs;

import dtu.projectapp.ui.InputDialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class ChangeEndDateDialog extends Dialog<ButtonType> {
    private TextField endDateField;
    private TextField endYearField;

    public ChangeEndDateDialog() {
        super();
        resetDialog();

    }

    public void resetDialog() {
        super.setTitle("Change activity deadline");
        VBox vbox = new VBox();

        endDateField = new TextField();
        endDateField.setPromptText("Enter week");
        endYearField = new TextField();
        endYearField.setPromptText("Enter year");

        vbox.getChildren().addAll(endDateField, endYearField);

        getDialogPane().setContent(vbox);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Handle the result
        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return ButtonType.OK;
            }
            return null;
        });
    }

    public String getEndWeek() {
        return endDateField.getText();
    }

    public String getEndYear() {
        return endYearField.getText();
    }
}
