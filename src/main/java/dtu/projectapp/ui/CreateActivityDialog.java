package dtu.projectapp.ui;

import javafx.scene.control.TextInputDialog;

public class CreateActivityDialog extends InputDialog {
    public CreateActivityDialog() {
        resetDialog();
    }

    @Override
    public void resetDialog() {
        super.dialog = new TextInputDialog("");
        super.dialog.setTitle("add Activity To Project");
        super.dialog.setHeaderText("Enter the name of the Activity to add to the Project");
        
    }
}
