package dtu.projectapp.ui;

import javafx.scene.control.TextInputDialog;

public class AddEmployeeDialog extends InputDialog { // Noah
    public AddEmployeeDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("Add employee");
        super.dialog.setHeaderText("Enter the id of the new employee");
    }
}
