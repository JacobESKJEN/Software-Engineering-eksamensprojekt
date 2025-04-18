package dtu.projectapp.ui;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;

public class CreateProjectDialog {
    private TextInputDialog dialog;

    public CreateProjectDialog() {
        resetDialog();
    }

    public void resetDialog() {
        dialog = new TextInputDialog("");
        dialog.setTitle("Create project");
        dialog.setHeaderText("Enter the name of the project");
    }

    public String getProjectName() {
        resetDialog();
        Optional<String> result = dialog.showAndWait();

        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        return entered;
    }
}
