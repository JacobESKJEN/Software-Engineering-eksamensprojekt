package dtu.projectapp.ui.ChangeActivityDialogs;

import dtu.projectapp.ui.InputDialog;
import javafx.scene.control.TextInputDialog;

public class ChangeEndDateDialog extends InputDialog {
    public ChangeEndDateDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("Change activity deadline");
        super.dialog.setHeaderText("Enter the new deadline of the activity");
    }
}
