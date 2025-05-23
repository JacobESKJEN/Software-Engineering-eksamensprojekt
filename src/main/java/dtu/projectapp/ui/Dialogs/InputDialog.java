package dtu.projectapp.ui.Dialogs;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public abstract class InputDialog { // Jacob
    protected TextInputDialog dialog;

    public void resetDialog() {

    }

    public String getResult() {
        resetDialog();
        Optional<String> result = dialog.showAndWait();

        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        return entered;
    }
}
