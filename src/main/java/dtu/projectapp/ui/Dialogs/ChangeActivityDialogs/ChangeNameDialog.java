package dtu.projectapp.ui.Dialogs.ChangeActivityDialogs;

import dtu.projectapp.ui.Dialogs.InputDialog;
import javafx.scene.control.TextInputDialog;

public class ChangeNameDialog extends InputDialog { // Jacob
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
