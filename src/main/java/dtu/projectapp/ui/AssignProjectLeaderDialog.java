package dtu.projectapp.ui;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class AssignProjectLeaderDialog extends Dialog {
    public AssignProjectLeaderDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("Assign project leader");
        super.dialog.setHeaderText("Enter the id of the project leader");
    }
}
