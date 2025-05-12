package dtu.projectapp.ui.Dialogs;

import javafx.scene.control.TextInputDialog;

public class AssignProjectLeaderDialog extends InputDialog { // Jacob
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
