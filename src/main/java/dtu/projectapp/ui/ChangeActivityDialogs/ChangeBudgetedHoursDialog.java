package dtu.projectapp.ui.ChangeActivityDialogs;

import dtu.projectapp.ui.InputDialog;
import javafx.scene.control.TextInputDialog;

public class ChangeBudgetedHoursDialog extends InputDialog {
    public ChangeBudgetedHoursDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("Change activity budgeted hours");
        super.dialog.setHeaderText("Enter the budgeted hours of the activity");
    }
}
