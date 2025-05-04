package dtu.projectapp.ui;

import javafx.scene.control.TextInputDialog;


public class CreateProjectDialog extends InputDialog {
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
