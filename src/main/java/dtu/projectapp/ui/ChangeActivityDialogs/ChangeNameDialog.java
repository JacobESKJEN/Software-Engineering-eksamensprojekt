package dtu.projectapp.ui.ChangeActivityDialogs;

import dtu.projectapp.ui.InputDialog;
import javafx.scene.control.TextInputDialog;

public class ChangeNameDialog extends InputDialog {
    public ChangeNameDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("Change activity name");
        super.dialog.setHeaderText("Enter the name of the activity");
    }
}
