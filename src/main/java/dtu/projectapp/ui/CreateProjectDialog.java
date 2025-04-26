package dtu.projectapp.ui;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;

public class CreateProjectDialog extends Dialog {
    public CreateProjectDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("Create project");
        super.dialog.setHeaderText("Enter the name of the project");
    }
}
